package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.comment.sdi.*;
import com.green.dto.comment.sdo.*;
import com.green.dto.common.pagination.PageInfo;
import com.green.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    //("[Tạo mới]")
    public ApiResponse<CommentCreateSdo> create(
            CommentCreateSdi req
    ) throws IOException {
        var rs = commentService.create(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/search")
    //("[Tìm kiếm]")
    public ApiResponse<List<CommentSearchSdo>> search(
            CommentSearchSdi req
    ) {
        var rs = commentService.search(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/self")
    //("[chi tiết]")
    public ApiResponse<CommentSelfSdo> self(
            CommentSelfSdi req
    ) {
        var rs = commentService.self(req);
        return new ApiResponse(rs);
    }


    @PostMapping("/update")
    //("[Cập nhập]")
    public ApiResponse<CommentUpdateSdo> update(
            CommentUpdateSdi req
    ) throws IOException {
        var rs = commentService.update(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
    //("[Xóa]")
    public ApiResponse<CommentDeleteSdo> delete(
            @RequestBody @Valid CommentDeleteSdi req
    ) {
        var rs = commentService.delete(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/like")
    public ApiResponse<CommentLikeSdo> like(
            @RequestBody @Valid CommentLikeSdi req
    ){
        var rs = commentService.like(req);
        return new ApiResponse<>(rs);
    }

    @PostMapping("/unlike")
    public ApiResponse<CommentUnlikeSdo> like(
            @RequestBody @Valid CommentUnlikeSdi req
    ){
        var rs = commentService.unlike(req);
        return new ApiResponse<>(rs);
    }
}
