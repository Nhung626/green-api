package com.green.dto.tree.sdo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TreeSelfSdo {
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String name;

    private String typeTree;

    private Long imgId;

    private Long landId;
    //private Long landName;
    private Long gardenId;
}
