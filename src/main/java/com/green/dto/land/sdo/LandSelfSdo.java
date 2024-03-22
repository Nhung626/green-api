package com.green.dto.land.sdo;

import org.springframework.web.multipart.MultipartFile;

public class LandSelfSdo {
    private Long id;

    private String name;

    private String address;

    private float width;

    private float length;

    private MultipartFile img;

    private Long userId;

    private Long gardenId;
}
