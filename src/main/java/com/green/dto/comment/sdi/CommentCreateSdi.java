package com.green.dto.comment.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.green.constants.LabelKey.*;

@Data
public class CommentCreateSdi {
    @Validation(label = LABEL_STATUS_ID, required = true)
    private Long statusId;

    @Validation(label = LABEL_COMMENT_PARENT_ID)
    private Long parentId;

    @Validation(label = LABEL_COMMENT_CONTENT, required = true)
    private String content;

    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;
}
