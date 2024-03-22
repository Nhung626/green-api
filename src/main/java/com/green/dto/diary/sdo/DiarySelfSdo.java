package com.green.dto.diary.sdo;

import lombok.Data;

import java.util.List;

@Data
public class DiarySelfSdo {
    private List<Long> imgIds;
    private String description;
    private Long landId;
    private Long treeId;
}
