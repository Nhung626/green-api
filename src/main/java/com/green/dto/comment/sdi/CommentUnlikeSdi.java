package com.green.dto.comment.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_COMMENT_ID;
import static com.green.constants.LabelKey.LABEL_USER_ID;
@Data
public class CommentUnlikeSdi {
    @Validation(label = LABEL_COMMENT_ID, required = true)
    private Long commentId;
}
