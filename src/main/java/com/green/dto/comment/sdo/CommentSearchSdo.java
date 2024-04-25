package com.green.dto.comment.sdo;

import com.green.dto.common.CommonSdo;
import lombok.Data;

import java.sql.Date;

@Data
public class CommentSearchSdo {
    private Long id;

    private Long statusId;

    private Long userId;

    private Long parentId;

    private String content;

    private String createdAt;

    private Boolean isEdited;

    private Long countLike;

    private Integer countReply;

    private Boolean userLiked;
}
