package com.green.dto.user.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.*;

@Data
public class UserUpdateSdi {
    @Validation(label = LABEL_USER_ID, required = true)
    private Long id;

    @Validation(label = LABEL_USER_EMAIL, required = true)
    private String email;
}
