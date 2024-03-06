package com.green.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "file_attachment")
public class File extends AbstractAudit {

    @Column(name = "file_key")
    private String key;

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_path")
    private String path;

    @Column(name = "file_size")
    private Long size;

    @Column(name = "file_ext")
    private String extension;

    @Column(name = "is_temp")
    private Boolean isTemp;
}
