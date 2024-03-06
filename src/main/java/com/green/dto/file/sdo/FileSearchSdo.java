package com.green.dto.file.sdo;

import com.green.dto.common.CommonSdo;
import lombok.Data;


@Data
public class FileSearchSdo extends CommonSdo {
    private Long id;

    private String key;

    private String name;

    private String path;

    private Double size;

    private String extension;

    private Boolean isTemp;
}
