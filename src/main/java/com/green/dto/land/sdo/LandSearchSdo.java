package com.green.dto.land.sdo;

import lombok.Data;

@Data
public class LandSearchSdo {
    private Long id;

    private String name;

    private String address;

    private String area;

    private Long imgId;

    private Long userId;
}
