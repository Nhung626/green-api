package com.green.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "status")
public class Status extends AbstractAudit{
    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "media")
    private List<Media> medias;

    @Column(name = "content")
    private String content;
}
