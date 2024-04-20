package com.green.model;

import com.green.constants.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tree")
public class Tree extends AbstractAudit{
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "img_id")
    private  Long imgId;

    @Column(name = "land_id")
    private  Long landId;

    @Column(name = "garden_id")
    private Long gardenId;

    @Column(name = "sum")
    private int sum;

    @Column(name = "state")
    private State state;
}
