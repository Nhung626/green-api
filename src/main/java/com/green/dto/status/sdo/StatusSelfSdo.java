package com.green.dto.status.sdo;

import lombok.Data;

import java.util.List;

@Data
public class StatusSelfSdo {
    private Long id;

    private Long userId;

    private List<Long> imgIds;

    private String content;

    private String createdAt;

    //status đã chỉnh sửa hay chưa
    private String updatedAt;
    //Số lượng likes của status
    private Long countLike;

    //Người dùng đã like status này hay chưa
    private Boolean userLiked;
}
