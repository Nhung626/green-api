package com.green.dto.gardeninfo.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.green.constants.LabelKey.*;

@Data
public class GardenInfoUpdateSdi {
    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;

    @Validation(label = LABEL_GARDEN_INFO_NAME, required = true)
    private String name;

    @Validation(label = LABEL_GARDEN_INFO_COVER, required = true)
    private MultipartFile cover;

    @Validation(label = LABEL_GARDEN_INFO_DESCRIPTION, required = true)
    private String description;
}
