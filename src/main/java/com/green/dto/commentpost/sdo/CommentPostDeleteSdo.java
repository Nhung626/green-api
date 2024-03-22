package com.green.dto.commentpost.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class CommentPostDeleteSdo {
    private Boolean success;
}
