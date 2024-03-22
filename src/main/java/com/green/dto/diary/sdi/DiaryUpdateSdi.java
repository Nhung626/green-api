package com.green.dto.diary.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.green.constants.LabelKey.*;
import static com.green.constants.LabelKey.LABEL_LAND_ID;

@Data
public class DiaryUpdateSdi {
    @Validation(label = LABEL_DIARY_ID, required = true)
    private Long id;

    @Validation(label = LABEL_DIARY_DESCRIPTION, required = true)
    private String description;

    @Validation(label = LABEL_DIARY_IMG, required = true)
    private List<MultipartFile> images;

    @Validation(label = LABEL_TREE_ID, required = true)
    private Long treeId;

    @Validation(label = LABEL_LAND_ID, required = true)
    private Long landId;
}
