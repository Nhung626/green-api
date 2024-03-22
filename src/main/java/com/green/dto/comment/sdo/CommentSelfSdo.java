package com.green.dto.comment.sdo;

import lombok.Data;

@Data
public class CommentSelfSdo {
    private Long id;

    private Long statusId;

    private Long userId;

    private Long parentId;

    private String content;

    private Boolean isEdited;

    private Long countLike;

    private Boolean userLiked;
}
