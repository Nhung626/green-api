package com.green.dto.commentpost.sdo;

import lombok.Data;

@Data
public class CommentPostSelfSdo {
    private Long id;

    private Long statusId;

    private Long userId;

    private Long parentId;

    private String content;
}
