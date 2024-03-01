package com.green.dto.user.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.*;

@Data
public class UserCreateSdi {
    @Validation(label = LABEL_USER_NAME, required = true)
    private String name;

    @Validation(label = LABEL_USER_EMAIL, required = true)
    private String email;

    @Validation(label = LABEL_USER_PASSWORD, required = true)
    private String password;

}
