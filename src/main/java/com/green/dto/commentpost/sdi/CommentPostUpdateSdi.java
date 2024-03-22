package com.green.dto.commentpost.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.*;
@Data
public class CommentPostUpdateSdi {
    @Validation(label = LABEL_COMMENT_ID, required = true)
    private Long id;

    @Validation(label = LABEL_COMMENT_PARENT_ID, required = true)
    private Long statusId;

    @Validation(label = LABEL_COMMENT_USER_ID, required = true)
    private Long userId;

    @Validation(label = LABEL_COMMENT_PARENT_ID, required = true)
    private Long parentId;

   @Validation(label = LABEL_COMMENT_CONTENT)
    private String content;
}
