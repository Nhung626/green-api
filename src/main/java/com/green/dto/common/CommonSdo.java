package com.green.dto.common;

import com.fasterxml.jackson.annotation.JsonFormat;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

import static com.green.utils.DateTimeUtils.DATE_FORMAT2;

@Data
public abstract class CommonSdo {
//    @ApiModelProperty(notes = "Trạng thái")
    private Integer status;

//    @ApiModelProperty(notes = "Thời gian tạo")
    @JsonFormat(pattern = DATE_FORMAT2)
    private Date createdAt;

//    @ApiModelProperty(notes = "Thời gian cập nhập")
    @JsonFormat(pattern = DATE_FORMAT2)
    private Date updatedAt;

//    @ApiModelProperty(notes = "Người tạo")
    private Long createdBy;

//    @ApiModelProperty(notes = "Người cập nhập")
    private Long updatedBy;
}
