package com.green.dto.common.pagination;

import lombok.Data;

import static com.green.utils.DataUtil.isNullObject;

@Data
public class PageInfo {
//    @Schema(description = "Số trang hiện tại", example = "1", required = true)
    private Integer currentPage = 0;

//    @Schema(description = "Số bản ghi một trang", example = "5", required = true)
    private Integer pageSize;

    public Integer getCurrentPage() {
        return isNullObject(currentPage) ? 0 : currentPage;
    }

    public Integer getPageSize() {
        return isNullObject(pageSize) ? Integer.MAX_VALUE : pageSize;
    }
}
