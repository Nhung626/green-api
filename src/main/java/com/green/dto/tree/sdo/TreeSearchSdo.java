package com.green.dto.tree.sdo;


import lombok.Data;

import java.time.LocalDate;

@Data
public class TreeSearchSdo {
    private Long id;

    private String startDate;

    private String endDate;

    private String name;

    private String type;

    private Long imgId;

    private Long landId;

    private Long gardenId;
}
