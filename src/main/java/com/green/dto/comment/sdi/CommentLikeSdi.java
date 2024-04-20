package com.green.dto.comment.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.*;

@Data
public class CommentLikeSdi {
    @Validation(label = LABEL_COMMENT_ID, required = true)
    private Long commentId;

}
