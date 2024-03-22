package com.green.dto.status.sdo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StatusSelfSdo {
    private Long id;

    private Long userId;

    private Long imgId;

    private String content;

    private LocalDateTime publishTime;

    //status đã chỉnh sửa hay chưa
    private Boolean isEdited;

    //Số lượng likes của status
    private Long countLike;

    //Người dùng đã like status này hay chưa
    private Boolean userLiked;
}
