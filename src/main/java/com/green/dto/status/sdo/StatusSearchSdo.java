package com.green.dto.status.sdo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StatusSearchSdo {
    private Long id;

    private Long userId;

    private List<Long> imgIds;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    //Số lượng likes của status
    private Long countLike;

    //Người dùng đã like status này hay chưa
    private Boolean userLiked;
}
