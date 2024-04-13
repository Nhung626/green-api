package com.green.dto.commentpost.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.*;

@Data
public class CommentPostCreateSdi {
    
    @Validation(label = LABEL_POST_ID, required = true)
    private Long postId;

    @Validation(label = LABEL_COMMENT_PARENT_ID)
    private Long parentId;

    @Validation(label = LABEL_COMMENT_CONTENT, required = true)
    private String content;

    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;
}
