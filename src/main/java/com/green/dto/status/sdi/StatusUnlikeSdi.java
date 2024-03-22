package com.green.dto.status.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_STATUS_ID;
import static com.green.constants.LabelKey.LABEL_USER_ID;
@Data
public class StatusUnlikeSdi {
    @Validation(label = LABEL_STATUS_ID, required = true)
    private Long statusId;

    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;
}
