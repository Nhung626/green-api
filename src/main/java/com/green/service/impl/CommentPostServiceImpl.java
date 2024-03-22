package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.commentpost.sdi.*;
import com.green.dto.commentpost.sdo.*;
import com.green.dto.common.pagination.PageInfo;
import com.green.exception.AppException;
import com.green.model.CommentPost;
import com.green.model.LikeCmtPost;
import com.green.repository.CommentPostRepo;
import com.green.repository.LikeCommentPostRepo;
import com.green.service.CommentPostService;
import com.green.service.MediaService;
import com.green.service.common.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.green.constants.LabelKey.*;
import static com.green.constants.LabelKey.LABEL_COMMENT_UNLIKE;
import static com.green.utils.DataUtil.copyProperties;

@Service
@RequiredArgsConstructor
public class CommentPostServiceImpl implements CommentPostService {
    private final CommentPostRepo commentPostRepo;
    private final LikeCommentPostRepo likeCommentPostRepo;
    private final CommonService commonService;
    private final MediaService mediaService;

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
    public Page<CommentPostSearchSdo> search(CommentPostSearchSdi req, PageInfo pageInfo) {
        Long userId = commonService.getIdLogin();
        return commentPostRepo.search(req, pageInfo, userId);
    }

    @Override
    public CommentPostSelfSdo self(CommentPostSelfSdi req) {
        var comment = getCommentPost(req.getId());
        return copyProperties(comment, CommentPostSelfSdo.class);
    }

    @Override
    public CommentPostLikeSdo like(CommentPostLikeSdi req) {
        var userId = req.getUserId();
        var commentId = req.getCommentId();

        Optional<LikeCmtPost> existingLike = likeCommentPostRepo.findByUserIdAndCommentId(userId, commentId);
        if (existingLike.isPresent()) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT_LIKE));
        }

        var newLike = new LikeCmtPost();
        newLike.setUserId(userId);
        newLike.setCommentPostId(commentId);

        likeCommentPostRepo.save(newLike);
        return CommentPostLikeSdo.of(true);
    }

    @Override
    public CommentPostUnlikeSdo unLike(CommentPostUnlikeSdi req) {
        var userId = req.getUserId();
        var commentId = req.getCommentId();

        Optional<LikeCmtPost> existingUnLike = likeCommentPostRepo.findByUserIdAndCommentId(userId, commentId);

        if (existingUnLike.isPresent()) {
            likeCommentPostRepo.deleteLike(userId, commentId);
            return CommentPostUnlikeSdo.of(true);
        }
        throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT_UNLIKE));
    }

    public CommentPost getCommentPost(Long id){
        return commentPostRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT, id)));
    }
}
