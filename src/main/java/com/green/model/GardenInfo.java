package com.green.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "garden_info")
public class GardenInfo extends AbstractAudit{
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "cover_id")
    private Long coverId;

    @Column(name = "description")
    private String description;

}
