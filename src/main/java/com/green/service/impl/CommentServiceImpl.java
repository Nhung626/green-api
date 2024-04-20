package com.green.service.impl;

import com.green.constants.Const;
import com.green.dto.comment.sdi.*;
import com.green.dto.comment.sdo.*;
import com.green.exception.AppException;
import com.green.model.Comment;
import com.green.model.Like;
import com.green.model.LikeComment;
import com.green.repository.CommentRepo;
import com.green.repository.LikeCommentRepo;
import com.green.service.CommentService;
import com.green.service.common.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.green.constants.LabelKey.*;
import static com.green.utils.DataUtil.copyProperties;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final LikeCommentRepo likeCommentRepo;
    private final CommonService commonService;

    @Override
    public CommentCreateSdo create(CommentCreateSdi req) {
        if (req.getParentId() != null) {
            getComment(req.getParentId());
        }

        var comment = new Comment();
        comment.setStatusId(req.getStatusId());
        comment.setParentId(req.getParentId());
        comment.setContent(req.getContent());

        comment.setUserId(commonService.getIdLogin());
        commentRepo.save(comment);

        return CommentCreateSdo.of(comment.getId());
    }

    @Override
    public CommentUpdateSdo update(CommentUpdateSdi req) {
        var comment = getComment(req.getId());
        if (!comment.getUserId().equals(commonService.getIdLogin())) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT));
        }

        comment.setContent(req.getContent());
        commentRepo.save(comment);

        return CommentUpdateSdo.of(comment.getId());
    }

    @Override
    public CommentDeleteSdo delete(CommentDeleteSdi req) {
        var comment = getComment(req.getId());
        if (!comment.getUserId().equals(commonService.getIdLogin())) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT));
        }

        comment.setStatus(Const.GeneralStatus.DELETED);
        commentRepo.save(comment);

        return CommentDeleteSdo.of(true);
    }

    @Override
    public List<CommentSearchSdo> search(CommentSearchSdi req) {
        Long userId = commonService.getIdLogin();
        return commentRepo.search(req, userId);
    }

    @Override
    public CommentSelfSdo self(CommentSelfSdi req) {
        var comment = getComment(req.getId());
        return copyProperties(comment, CommentSelfSdo.class);
    }

    @Override
    public CommentLikeSdo like(CommentLikeSdi req) {
        Long userId = commonService.getIdLogin();
        Long commentId = req.getCommentId();

        Optional<Like> existingLike = likeCommentRepo.findByUserIdAndCommentId(userId, commentId);
        if (existingLike.isPresent()) {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT_LIKE));
        }

        var newLike = new LikeComment(userId, commentId);

        likeCommentRepo.save(newLike);
        return CommentLikeSdo.of(true);
    }

    @Override
    public CommentUnlikeSdo unlike(CommentUnlikeSdi req) {
        Long userId = commonService.getIdLogin();
        Long commentId = req.getCommentId();

        Optional<Like> existingUnLike = likeCommentRepo.findByUserIdAndCommentId(userId, commentId);

        if (existingUnLike.isPresent()) {
            likeCommentRepo.deleteLike(userId, commentId);
            return CommentUnlikeSdo.of(true);
        }
        throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT_UNLIKE));
    }

    public Comment getComment(Long id) {
        return commentRepo.findById(id)
                .orElseThrow(() -> new AppException(ERROR_NOT_EXIST, List.of(LABEL_COMMENT, id)));
    }
}
