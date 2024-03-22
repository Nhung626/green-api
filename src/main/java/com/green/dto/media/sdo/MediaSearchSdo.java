package com.green.dto.media.sdo;

import com.green.dto.common.CommonSdo;
import lombok.Data;


@Data
public class MediaSearchSdo extends CommonSdo {
    private Long id;

    private String name;

    private String type;
}
