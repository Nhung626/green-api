package com.green.dto.land.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.green.constants.LabelKey.*;
@Data
public class LandUpdateSdi {
    @Validation(label = LABEL_LAND_ID,required = true)
    private Long Id;

    @Validation(label = LABEL_LAND_NAME, required = true)
    private String name;

    @Validation(label =LABEL_LAND_ADDRESS, required = true)
    private String address;

    @Validation(label = LABEL_LAND_AREA, required = true)
    private float area;
    
    @Validation(label = LABEL_LAND_ID,required = true)
    private MultipartFile image;

    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;
}
