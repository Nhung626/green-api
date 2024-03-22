package com.green.dto.land.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_LAND_ID;
@Data
public class LandSelfSdi {
    @Validation(label = LABEL_LAND_ID, required = true)
    private Long id;
}
