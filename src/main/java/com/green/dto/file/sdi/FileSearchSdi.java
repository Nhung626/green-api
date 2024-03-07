package com.green.dto.file.sdi;

import com.green.utils.valid.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.green.constants.LabelKey.*;

@Data
@AllArgsConstructor(staticName = "of")
public class FileSearchSdi {
    @Validation(label = LABEL_FILE_NAME, required = true)
    private String name;

}
