package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.status.sdi.*;
import com.green.dto.status.sdo.*;
import com.green.service.StatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/status")
public class StatusController {
    private final StatusService statusService;
    @PostMapping("/create")
    //("[Tạo mới]")
    public ApiResponse<StatusCreateSdo> create(
            StatusCreateSdi req
    ) throws IOException {
        var rs = statusService.create(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/search")
    //("[Tìm kiếm]")
    public ApiResponse<List<StatusSearchSdo>> search(
            StatusSearchSdi req
    ){
        var rs = statusService.search(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/self")
    //("[chi tiết]")
    public ApiResponse<StatusSelfSdo> self(
            StatusSelfSdi req, Long userId
    ){
        var rs = statusService.self(req, userId);
        return new ApiResponse(rs);
    }


    @PostMapping("/update")
    //("[Cập nhập]")
    public ApiResponse<StatusUpdateSdo> update(
            StatusUpdateSdi req
    ) throws IOException {
        var rs = statusService.update(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
    //("[Xóa]")
    public ApiResponse<StatusDeleteSdo> delete(
            @RequestBody @Valid StatusDeleteSdi req
    ) {
        var rs = statusService.delete(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/like")
    public ApiResponse<StatusLikeSdo> like(
            @RequestBody @Valid StatusLikeSdi req
    ){
        var rs = statusService.like(req);
        return new ApiResponse<>(rs);
    }

    @PostMapping("/unlike")
    public ApiResponse<StatusUnlikeSdo> like(
            @RequestBody @Valid StatusUnlikeSdi req
    ){
        var rs = statusService.unlike(req);
        return new ApiResponse<>(rs);
    }
}
