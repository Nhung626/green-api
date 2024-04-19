package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.gardeninfo.sdi.*;
import com.green.dto.gardeninfo.sdo.*;
import com.green.service.GardenInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/garden-info")
public class GardenController {
    private final GardenInfoService gardenInfoService;

    @PostMapping("/create")
    //("[Tạo mới]")
    public ApiResponse<GardenInfoCreateSdo> create(
            GardenInfoCreateSdi req
    ) throws IOException {
        var rs = gardenInfoService.create(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/search")
    //("[Tìm kiếm]")
    public ApiResponse<List<GardenInfoSearchSdo>> search(
             GardenInfoSearchSdi req
    ){
        var rs = gardenInfoService.search(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/self")
    //("[chi tiết]")
    public ApiResponse<GardenInfoSelfSdo> self(
            @RequestBody GardenInfoSelfSdi req
    ){
        var rs = gardenInfoService.self(req);
        return new ApiResponse(rs);
    }


    @PostMapping("/update")
    //("[Cập nhập]")
    public ApiResponse<GardenInfoUpdateSdo> update(
            GardenInfoUpdateSdi req
    ) throws IOException {
        var rs = gardenInfoService.update(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/update-cover")
    //("[Cập nhập cover]")
    public ApiResponse<GardenInfoUpdateCoverSdo> updateCover(
            GardenInfoUpdateCoverSdi req
    ) throws IOException {
        var rs = gardenInfoService.uploadCover(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
    //("[Xóa]")
    public ApiResponse<GardenInfoDeleteSdo> delete(
            @RequestBody @Valid GardenInfoDeleteSdi req
    ) {
        var rs = gardenInfoService.delete(req);
        return new ApiResponse(rs);
    }
}
