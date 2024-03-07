package com.green.dto.gardeninfo.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_GARDEN_INFO_ID;

@Data
public class GardenInfoDeleteSdi {
    @Validation(label = LABEL_GARDEN_INFO_ID, required = true)
    private Long id;
}
