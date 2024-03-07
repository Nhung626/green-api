package com.green.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "land")

public class Land  extends AbstractAudit{
    @Column(name = "name")
    private String name;

    @Column(name = "adress")
    private String adress;

    @Column(name = "width")
    private float width;

    @Column(name = "length")
    private float length;

    @Column(name = "img_id")
    private Long imgId;

    @Column(name = "user_id")
    private Long userId;

    //@Column(name = "garden_id")
    //private Long gardenId;
}
