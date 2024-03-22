package com.green.dto.diary.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class DiarySearchSdo {
    private List<Long> imgIds;
    private String description;
    private Long landId;
    private Long treeId;
}
