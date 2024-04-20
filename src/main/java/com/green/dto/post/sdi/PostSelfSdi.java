package com.green.dto.post.sdi;

import com.green.utils.valid.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_POST_ID;
@Data
@AllArgsConstructor
public class PostSelfSdi {
    @Validation(label = LABEL_POST_ID, required = true)
    private Long id;
}
