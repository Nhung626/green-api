package com.green.dto.user.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_USER_ID;

@Data
public class UserSelfSdi {
    @Validation(label = LABEL_USER_ID, required = true)
    private Long id;
}
