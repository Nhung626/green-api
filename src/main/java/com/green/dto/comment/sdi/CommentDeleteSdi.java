package com.green.dto.comment.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_COMMENT_ID;
@Data
public class CommentDeleteSdi {
    @Validation(label = LABEL_COMMENT_ID)
    private Long id;
}
