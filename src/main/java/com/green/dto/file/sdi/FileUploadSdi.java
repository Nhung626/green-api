package com.green.dto.file.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import static com.green.constants.LabelKey.*;

@Data
public class FileUploadSdi {
    @Validation(label = LABEL_FILE, required = true)
    private MultipartFile file;

    public FileUploadSdi(MultipartFile file) {
        this.file = file;
    }
}
