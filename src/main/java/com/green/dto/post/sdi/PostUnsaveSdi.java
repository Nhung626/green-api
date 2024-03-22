package com.green.dto.post.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_POST_ID;
import static com.green.constants.LabelKey.LABEL_USER_ID;

@Data
public class PostUnsaveSdi {
    @Validation(label = LABEL_POST_ID, required = true)
    private Long postId;

    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;
}
