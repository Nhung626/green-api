package com.green.dto.userinfo.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_USER_ID;
import static com.green.constants.LabelKey.LABEL_USER_INFO_ID;

@Data
public class UserInfoDeleteSdi {
    @Validation(label = LABEL_USER_INFO_ID, required = true)
    private Long id;
}
