package com.green.dto.diary.sdi;

import com.green.utils.valid.Validation;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_DIARY_ID;
@Data
public class DiarySelfSdi {
    @Validation(label = LABEL_DIARY_ID, required = true)
    private Long id;
}
