package com.green.dto.userinfo.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_USER_ID;
@Data
public class UserInfoSearchSdi {
    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;
}
