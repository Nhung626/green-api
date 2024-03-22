package com.green.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diary")
public class Diary extends AbstractAudit{
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "media")
    private List<Media> medias;

    @Column(name = "tree_id")
    private Long treeId;

    @Column(name = "land_id")
    private Long landId;
}
