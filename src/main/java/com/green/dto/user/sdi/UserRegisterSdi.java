package com.green.dto.user.sdi;

import com.green.utils.valid.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.green.constants.LabelKey.*;

@Data
@AllArgsConstructor(staticName = "of")
public class UserRegisterSdi {

    @Validation(label = LABEL_USER_EMAIL, required = true)
    private String email;

    @Validation(label = LABEL_USER_PASSWORD, required = true)
    private String password;
}
