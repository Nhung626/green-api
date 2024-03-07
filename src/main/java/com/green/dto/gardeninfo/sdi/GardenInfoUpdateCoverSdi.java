package com.green.dto.gardeninfo.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.green.constants.LabelKey.LABEL_GARDEN_INFO_COVER;
import static com.green.constants.LabelKey.LABEL_GARDEN_INFO_ID;

@Data
public class GardenInfoUpdateCoverSdi {
    @Validation(label = LABEL_GARDEN_INFO_ID, required = true)
    private Long userId;

    @Validation(label = LABEL_GARDEN_INFO_COVER, required = true)
    private MultipartFile cover;
}
