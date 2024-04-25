package com.green.dto.status.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_USER_ID;
@Data
public class StatusSearchSdi {
    private String keywords;

    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;

}
