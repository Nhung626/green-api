package com.green.dto.land.sdo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LandSelfSdo {
    private Long id;

    private String name;

    private String address;

    private float area;

    private Long imgId;

    private Long userId;

    private Long gardenId;
}
