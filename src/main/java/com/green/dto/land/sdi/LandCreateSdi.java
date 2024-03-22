package com.green.dto.land.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.green.constants.LabelKey.*;
@Data
public class LandCreateSdi{
    @Validation(label = LABEL_LAND_NAME, required = true)
    private String name;

    @Validation(label =LABEL_LAND_ADDRESS, required = true)
    private String address;

    @Validation(label = LABEL_LAND_WIDTH, required = true)
    private float width;

    @Validation(label = LABEL_LAND_LENGTH, required = true)
    private float length;

    @Validation(label = LABEL_LAND_IMG, required = true)
    private MultipartFile img;

    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;

    @Validation(label = LABEL_GARDEN_INFO_ID, required = true)
    private Long gardenId;
}
