package com.green.dto.comment.sdo;

import com.green.dto.common.CommonSdo;
import lombok.Data;

@Data
public class CommentSearchSdo extends CommonSdo {
    private Long id;

    private Long statusId;

    private Long userId;

    private Long parentId;

    private String content;

    private Boolean isEdited;

    private Long countLike;

    private Integer countReply;

    private Boolean userLiked;
}
