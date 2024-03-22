package com.green.dto.status.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.green.constants.LabelKey.*;

@Data
public class StatusCreateSdi {
    @Validation(label = LABEL_USER_ID, required = true)
    private Long userId;

    @Validation(label = LABEL_STATUS_IMG)
    private List<MultipartFile> img;

    @Validation(label = LABEL_STATUS_CONTENT)
    private String content;
}
