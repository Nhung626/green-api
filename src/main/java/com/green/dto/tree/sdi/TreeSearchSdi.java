package com.green.dto.tree.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_GARDEN_INFO_ID;
import static com.green.constants.LabelKey.LABEL_LAND_ID;
@Data
public class TreeSearchSdi {
    @Validation(label = LABEL_LAND_ID)
    private  Long landId;

    @Validation(label = LABEL_GARDEN_INFO_ID)
    private Long gardenId;
}
