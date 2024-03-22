package com.green.dto.commentpost.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_POST_ID;
@Data
public class CommentPostSearchSdi {
    @Validation(label = LABEL_POST_ID, required = true)
    private Long postId;

    private Long parentId;
}
