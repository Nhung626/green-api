package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.commentpost.sdi.*;
import com.green.dto.commentpost.sdo.*;
import com.green.exception.AppException;
import com.green.model.CommentPost;
import com.green.model.LikeCommentPost;
import com.green.repository.CommentPostRepo;
import com.green.repository.LikeCommentPostRepo;
import com.green.service.CommentPostService;
import com.green.service.common.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.green.constants.LabelKey.*;
import static com.green.utils.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class CommentPostServiceImpl implements CommentPostService {
    private final CommentPostRepo commentPostRepo;
    private final LikeCommentPostRepo likeCommentPostRepo;
    private final CommonService commonService;

    @Override
    public CommentPostCreateSdo create(CommentPostCreateSdi req) {
        if (req.getParentId() != null) {
            getCommentPost(req.getParentId());
        }

        var comment = new CommentPost();
        comment.setPostId(req.getPostId());
        comment.setParentId(req.getParentId());
        comment.setContent(req.getContent());

        comment.setUserId(commonService.getIdLogin());
        commentPostRepo.save(comment);

        return CommentPostCreateSdo.of(comment.getId());
    }

    @Override
    public CommentPostUpdateSdo update(CommentPostUpdateSdi req) {
        var comment = getCommentPost(req.getId());
        if (!comment.getUserId().equals(commonService.getIdLogin())) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT));
        }

        comment.setContent(req.getContent());
        commentPostRepo.save(comment);

        return CommentPostUpdateSdo.of(comment.getId());
    }

    @Override
    public CommentPostDeleteSdo delete(CommentPostDeleteSdi req) {
        var comment = getCommentPost(req.getId());
        if (!comment.getUserId().equals(commonService.getIdLogin())) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT));
        }

        comment.setStatus(Const.GeneralStatus.DELETED);
        commentPostRepo.save(comment);

        return CommentPostDeleteSdo.of(true);
    }

    @Override
    public List<CommentPostSearchSdo> search(CommentPostSearchSdi req) {
        Long userId = commonService.getIdLogin();
        return commentPostRepo.search(req, userId);
    }

    @Override
    public CommentPostSelfSdo self(CommentPostSelfSdi req) {
        var comment = getCommentPost(req.getId());
        return copyProperties(comment, CommentPostSelfSdo.class);
    }

    @Override
    public CommentPostLikeSdo like(CommentPostLikeSdi req) {
        var userId = commonService.getIdLogin();
        var commentPostId = req.getCommentPostId();

        Optional<LikeCommentPost> existingLike = likeCommentPostRepo.findByUserIdAndCommentPostId(userId, commentPostId);
        if (existingLike.isPresent()) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT_LIKE));
        }

        var newLike = new LikeCommentPost(userId, commentPostId);

        likeCommentPostRepo.save(newLike);
        return CommentPostLikeSdo.of(true);
    }

    @Override
    public CommentPostUnlikeSdo unlike(CommentPostUnlikeSdi req) {
        var userId = commonService.getIdLogin();
        var commentPostId = req.getCommentPostId();

        Optional<LikeCommentPost> existingUnLike = likeCommentPostRepo.findByUserIdAndCommentPostId(userId, commentPostId);

        if (existingUnLike.isPresent()) {
            likeCommentPostRepo.deleteLike(userId, commentPostId);
            return CommentPostUnlikeSdo.of(true);
        }
        throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT_UNLIKE));
    }

    public CommentPost getCommentPost(Long id) {
        return commentPostRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT, id)));
    }
}
