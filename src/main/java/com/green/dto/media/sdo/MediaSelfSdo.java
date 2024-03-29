package com.green.dto.media.sdo;

import com.green.dto.common.CommonSdo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MediaSelfSdo extends CommonSdo {
    private Long id;

    private String name;

    private String type;

    private String path;
}
