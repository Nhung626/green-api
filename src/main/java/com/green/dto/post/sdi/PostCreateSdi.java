package com.green.dto.post.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.green.constants.LabelKey.*;
@Data
public class PostCreateSdi {
    @Validation(label = LABEL_POST_AUTH, required = true)
    private String auth;

    @Validation(label = LABEL_POST_TITlE, required = true)
    private String title;

    //loại cây
    @Validation(label = LABEL_POST_TYPE_TREE, required = true)
    private String typeTree;

    @Validation(label = LABEL_POST_GENERAL, required = true)
    private String general;

    //mô tả
    @Validation(label = LABEL_POST_DESCRIPTION, required = true)
    private String description;

    //Nội dung chính (cách chăm sóc, các loại sâu bệnh)
    @Validation(label = LABEL_POST_TAKE_CARE, required = true)
    private String takeCare;

    @Validation(label = LABEL_FILE)
    private List<MultipartFile> images;
}
