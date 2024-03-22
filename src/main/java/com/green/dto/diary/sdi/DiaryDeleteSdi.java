package com.green.dto.diary.sdi;

import com.green.utils.valid.Validation;
import lombok.Data;

import static com.green.constants.LabelKey.LABEL_DIARY_ID;

@Data
public class DiaryDeleteSdi {
    @Validation(label = LABEL_DIARY_ID, required = true)
    private Long id;
}
