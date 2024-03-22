package com.green.dto.media.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.green.constants.LabelKey.*;

@Data
public class MediaUploadSdi {
    @Validation(label = LABEL_FILE, required = true)
    private MultipartFile file;

    public MediaUploadSdi(MultipartFile file) {
        this.file = file;
    }
}
