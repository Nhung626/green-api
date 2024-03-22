package com.green.dto.media.sdi;

import com.green.constants.LabelKey;
import com.green.utils.valid.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class MediaSelfSdi {
    @Validation(label = LabelKey.LABEL_FILE_ID,required = true)
    private Long id;
}
