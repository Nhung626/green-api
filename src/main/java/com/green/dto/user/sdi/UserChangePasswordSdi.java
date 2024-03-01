package com.green.dto.user.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_USER_PASSWORD;

@Data
public class UserChangePasswordSdi {
    @Validation(label = LABEL_USER_PASSWORD, required = true)
    private String oldPassword;

    @Validation(label = LABEL_USER_PASSWORD, required = true)
    private String newPassword;
}
