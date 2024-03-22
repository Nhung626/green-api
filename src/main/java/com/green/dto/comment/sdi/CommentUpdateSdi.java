package com.green.dto.comment.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.*;
@Data
public class CommentUpdateSdi {
    @Validation(label = LABEL_COMMENT_ID, required = true)
    private Long id;

    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;

   @Validation(label = LABEL_COMMENT_CONTENT)
    private String content;
}
