package com.green.dto.commentpost.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_COMMENT_ID;
import static com.green.constants.LabelKey.LABEL_USER_ID;

@Data
public class CommentPostLikeSdi {
    @Validation(label = LABEL_COMMENT_ID, required = true)
    private Long commentPostId;

}
