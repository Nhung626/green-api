package com.green.dto.status.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_USER_ID;
@Data
public class StatusSearchSdi {
    @Validation(label = LABEL_USER_ID, required = true)
    private String keywords;

    private Long userId;

}
