package com.green.dto.gardeninfo.sdo;

import lombok.Data;

@Data
public class GardenInfoSearchSdo {
    private Long id;
    private Long userId;
    private String name;
    private Long coverId;
    private String description;
}
