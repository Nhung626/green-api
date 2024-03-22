package com.green.dto.post.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_POST_TITlE;

@Data
public class PostSearchSdi {
    @Validation(label = LABEL_POST_TITlE, required = true)
    private String keyword;
}
