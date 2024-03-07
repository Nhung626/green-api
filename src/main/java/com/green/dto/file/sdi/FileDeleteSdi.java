package com.green.dto.file.sdi;

import com.green.utils.valid.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_FILE_ID;

@Data
@AllArgsConstructor(staticName = "of")
public class FileDeleteSdi {
    @Validation(label = LABEL_FILE_ID,required = true)
    private Long id;
}
