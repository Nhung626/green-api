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
@Table(name = "post")
public class Post extends AbstractAudit {
    @Column(name = "auth")
    private String auth;

    @Column(name = "title")
    private String title;

    //loại cây
    @Column(name = "type_tree")
    private String typeTree;

    //Giới thiệu chung
    @Column(name = "general")
    private String general;

    //mô tả, Thông tin chi tiết
    @Column(name = "description")
    private String description;

    //Nội dung chính (cách chăm sóc, các loại sâu bệnh)
    @Column(name = "take_care")
    private String takeCare;

    @OneToMany
    private List<Media> medias;
}
