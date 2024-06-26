package com.green.dto.tree.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

import static com.green.constants.LabelKey.*;
@Data
public class TreeCreateSdi {
    @Validation(label = LABEL_TREE_START_DATE, required = true)
    private LocalDate startDate;

    @Validation(label = LABEL_TREE_NAME)
    private String name;

    @Validation(label = LABEL_TREE_TYPE)
    private String type;

    @Validation(label = LABEL_TREE_IMG)
    private MultipartFile img;

    @Validation(label = LABEL_LAND_ID)
    private  Long landId;

    @Validation(label = LABEL_USER_ID)
    private Long userId;

    private  int sum;
}
