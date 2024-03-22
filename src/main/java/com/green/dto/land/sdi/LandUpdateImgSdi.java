package com.green.dto.land.sdi;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class LandUpdateImgSdi {
    private Long id;
    private MultipartFile img;
}
