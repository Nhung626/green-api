package com.green.dto.user.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_USER_EMAIL;
import static com.green.constants.LabelKey.LABEL_USER_NAME;

@Data
public class UserSearchSdi {
    @Validation(label = LABEL_USER_EMAIL, required = true)
    private String email;
}
