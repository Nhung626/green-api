package com.green.dto.media.sdi;

import com.green.utils.valid.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_FILE_ID;

@Data
@AllArgsConstructor(staticName = "of")
public class MediaDeleteSdi {
    @Validation(label = LABEL_FILE_ID,required = true)
    private Long id;
}
