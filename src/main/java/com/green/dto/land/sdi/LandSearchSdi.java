package com.green.dto.land.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.*;

@Data
public class LandSearchSdi {
    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;
}
