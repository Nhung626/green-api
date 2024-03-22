package com.green.dto.post.sdo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostSearchSdo {
    private Long id;

    private String auth;

    private String title;

    //loại cây
    private String typeTree;

    //mô tả
    private String description;

    //Nội dung chính (cách chăm sóc, các loại sâu bệnh)
    private String takeCare;

    private List<Long> imageIds;

    private int countLike;

    private int countSave;

    private Boolean userLiked;

    private Boolean userSaved;
}
