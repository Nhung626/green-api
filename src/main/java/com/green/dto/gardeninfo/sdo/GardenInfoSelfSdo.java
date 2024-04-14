package com.green.dto.gardeninfo.sdo;

import lombok.Data;

@Data
public class GardenInfoSelfSdo {
    private Long id;

    private Long userId;

    private String name;

    private Long coverId;

    private String description;
}
