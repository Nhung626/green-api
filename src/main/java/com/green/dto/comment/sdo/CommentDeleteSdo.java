package com.green.dto.comment.sdo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class CommentDeleteSdo {
    private Boolean success;
}
