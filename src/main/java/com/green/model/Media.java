package com.green.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "media")
public class Media extends AbstractAudit {
    @Column(name = "file_name")
    private String name;

    @Column(name = "type")
    private String type;
}
