package com.green.dto.commentpost.sdo;

import com.green.dto.common.CommonSdo;
import lombok.Data;

@Data
public class CommentPostSearchSdo {
    private Long id;

    private Long postId;

    private Long userId;

    private Long parentId;

    private String content;

    private Boolean isEdited;

    private Long countLike;

    private Integer countReply;

    private Boolean userLiked;

    private String createAt;
}
