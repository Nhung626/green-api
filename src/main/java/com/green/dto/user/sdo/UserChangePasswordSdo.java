package com.green.dto.user.sdo;

import com.green.utils.valid.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_USER_ID;
@Data
@AllArgsConstructor(staticName = "of")
public class UserChangePasswordSdo {
    private Long id;
}
