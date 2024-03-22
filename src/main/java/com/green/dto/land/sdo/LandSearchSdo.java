package com.green.dto.land.sdo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

//@Data
public class LandSearchSdo {
    private Long id;

    private String name;

    private String address;

    private float width;

    private float length;

    private Long img_id;

    private Long userId;

    private Long gardenId;

}
