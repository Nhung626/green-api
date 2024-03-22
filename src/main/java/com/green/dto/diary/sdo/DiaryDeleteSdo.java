package com.green.dto.diary.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class DiaryDeleteSdo {
    private boolean success;
}
