package com.green.dto.diary.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_TREE_ID;

@Data
public class DiarySearchSdi {
    @Validation(label = LABEL_TREE_ID, required = true)
    private Long treeId;
}
