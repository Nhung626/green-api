package com.green.dto.tree.sdo;


import lombok.Data;

@Data
public class TreeSearchSdo {
    private Long id;

    private String startDate;

    private String endDate;

    private String name;

    private String type;

    private Long imgId;

    private int sum;

    private int state;

    private Long landId;

    private String landName;

    private Long gardenId;
}
