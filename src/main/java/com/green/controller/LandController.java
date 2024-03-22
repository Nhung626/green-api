package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.land.sdi.*;
import com.green.dto.land.sdo.*;
import com.green.service.LandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/land")
public class LandController {
    private LandService landService;

    @PostMapping("/create")
    //("[Tạo mới]")
    public ApiResponse<LandCreateSdo> create(
            LandCreateSdi req
    ) throws IOException {
        var rs = landService.create(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/search")
    //("[Tìm kiếm]")
    public ApiResponse<List<LandSearchSdo>> search(
            LandSearchSdi req
    ) {
        var rs = landService.search(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/self")
    //("[chi tiết]")
    public ApiResponse<LandSelfSdo> self(
            LandSelfSdi req
    ) {
        var rs = landService.self(req);
        return new ApiResponse(rs);
    }


    @PostMapping("/update")
    //("[Cập nhập]")
    public ApiResponse<LandUpdateSdo> update(
            LandUpdateSdi req
    ) throws IOException {
        var rs = landService.update(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/update-img")
    //("[Cập nhập img]")
    public ApiResponse<LandUpdateImgSdo> update(
            LandUpdateImgSdi req
    ) throws IOException {
        var rs = landService.uploadImg(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
    //("[Xóa]")
    public ApiResponse<LandDeleteSdo> delete(
            @RequestBody @Valid LandDeleteSdi req
    ) {
        var rs = landService.delete(req);
        return new ApiResponse(rs);
    }
}
