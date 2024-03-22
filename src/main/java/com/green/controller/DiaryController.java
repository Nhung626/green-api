package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.diary.sdi.*;
import com.green.dto.diary.sdo.*;
import com.green.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping("/create")
    //("[Tạo mới]")
    public ApiResponse<DiaryCreateSdo> create(
            DiaryCreateSdi req
    ) throws IOException {
        var rs = diaryService.create(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/search")
    //("[Tìm kiếm]")
    public ApiResponse<List<DiarySearchSdo>> search(
            DiarySearchSdi req
    ) {
        var rs = diaryService.search(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/self")
    //("[chi tiết]")
    public ApiResponse<DiarySelfSdo> self(
            DiarySelfSdi req
    ) {
        var rs = diaryService.self(req);
        return new ApiResponse(rs);
    }


    @PostMapping("/update")
    //("[Cập nhập]")
    public ApiResponse<DiaryUpdateSdo> update(
            DiaryUpdateSdi req
    ) throws IOException {
        var rs = diaryService.update(req);
        return new ApiResponse(rs);
    }


    @PostMapping("/delete")
    //("[Xóa]")
    public ApiResponse<DiaryDeleteSdo> delete(
            @RequestBody @Valid DiaryDeleteSdi req
    ) {
        var rs = diaryService.delete(req);
        return new ApiResponse(rs);
    }
}
