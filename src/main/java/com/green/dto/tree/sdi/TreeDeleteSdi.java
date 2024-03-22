package com.green.dto.tree.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_TREE_ID;
@Data
public class TreeDeleteSdi {
    @Validation(label= LABEL_TREE_ID, required = true)
    private Long id;
}
