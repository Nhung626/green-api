package com.green.dto.tree.sdo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TreeSelfSdo {
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String name;

    private String type;

    private Long imgId;

    private int sum;

    private int state;

    private Long landId;

    private Long gardenId;
    //private Long landName;
}
