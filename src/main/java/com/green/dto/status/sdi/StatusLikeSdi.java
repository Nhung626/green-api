package com.green.dto.status.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.*;
@Data
public class StatusLikeSdi {
    @Validation(label = LABEL_STATUS_ID, required = true)
    private Long statusId;
}
