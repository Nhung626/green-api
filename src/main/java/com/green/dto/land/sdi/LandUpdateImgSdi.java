package com.green.dto.land.sdi;

import lombok.Data;

import static com.green.constants.LabelKey.LABEL_LAND_ID;

import org.springframework.web.multipart.MultipartFile;

import com.green.utils.valid.Validation;
@Data
public class LandUpdateImgSdi {
    @Validation(label = LABEL_LAND_ID,required = true)
    private Long id;

    @Validation(label = LABEL_LAND_ID,required = true)
    private MultipartFile image;
}
