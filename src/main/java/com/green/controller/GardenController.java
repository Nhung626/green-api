package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.gardeninfo.sdi.*;
import com.green.dto.gardeninfo.sdo.*;
import com.green.service.GardenInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/garden-info")
public class GardenController {
    private final GardenInfoService userInfoService;

    @PostMapping("/create")
    //("[Tạo mới]")
    public ApiResponse<GardenInfoCreateSdo> create(
            @RequestBody @Valid GardenInfoCreateSdi req
    ) {
        var rs = userInfoService.create(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/search")
    //("[Tìm kiếm]")
    public ApiResponse<Page<GardenInfoSearchSdo>> search(
            GardenInfoSearchSdi req
    ){
        var rs = userInfoService.search(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/self")
    //("[chi tiết]")
    public ApiResponse<GardenInfoSelfSdo> self(
            GardenInfoSelfSdi req
    ){
        var rs = userInfoService.self(req);
        return new ApiResponse(rs);
    }


    @PostMapping("/update")
    //("[Cập nhập]")
    public ApiResponse<GardenInfoUpdateSdo> update(
            @RequestBody @Valid GardenInfoUpdateSdi req
    ) {
        var rs = userInfoService.update(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/update-avata")
    //("[Cập nhập cover]")
    public ApiResponse<GardenInfoUpdateCoverSdo> update(
            @RequestBody @Valid GardenInfoUpdateCoverSdi req
    ) {
        var rs = userInfoService.uploadCover(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
    //("[Xóa]")
    public ApiResponse<GardenInfoDeleteSdo> delete(
            @RequestBody @Valid GardenInfoDeleteSdi req
    ) {
        var rs = userInfoService.delete(req);
        return new ApiResponse(rs);
    }
}
