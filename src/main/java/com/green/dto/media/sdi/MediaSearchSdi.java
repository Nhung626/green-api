package com.green.dto.media.sdi;

import com.green.utils.valid.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.green.constants.LabelKey.*;

@Data
@AllArgsConstructor(staticName = "of")
public class MediaSearchSdi {
    @Validation(label = LABEL_FILE_NAME, required = true)
    private String name;

}
