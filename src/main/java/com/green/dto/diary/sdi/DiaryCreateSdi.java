package com.green.dto.diary.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.green.constants.LabelKey.*;
import java.util.List;

@Data
public class DiaryCreateSdi {
    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;
    
    @Validation(label = LABEL_DIARY_DESCRIPTION, required = true)
    private String description;

    @Validation(label = LABEL_DIARY_IMG, required = true)
    private List<MultipartFile> images;

    @Validation(label = LABEL_TREE_ID, required = true)
    private Long treeId;
}
