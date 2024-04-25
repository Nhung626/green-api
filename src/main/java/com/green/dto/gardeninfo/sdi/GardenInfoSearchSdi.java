package com.green.dto.gardeninfo.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.*;

@Data
public class GardenInfoSearchSdi {
    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;

    @Validation(label = LABEL_GARDEN_INFO_NAME, required = true)
    private String name;

    public GardenInfoSearchSdi(Long userId) {
        this.userId = userId;
    }
}
