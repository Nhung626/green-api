package com.green.dto.comment.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_STATUS_ID;
@Data
public class CommentSearchSdi {
    @Validation(label = LABEL_STATUS_ID, required = true)
    private Long statusId;

    private Long parentId;
}
