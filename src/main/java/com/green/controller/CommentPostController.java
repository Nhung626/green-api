package com.green.controller;

import com.green.dto.comment.sdi.CommentLikeSdi;
import com.green.dto.comment.sdi.CommentUnlikeSdi;
import com.green.dto.comment.sdo.CommentLikeSdo;
import com.green.dto.comment.sdo.CommentUnlikeSdo;
import com.green.dto.commentpost.sdi.*;
import com.green.dto.commentpost.sdo.*;
import com.green.dto.common.ApiResponse;
import com.green.dto.common.pagination.PageInfo;
import com.green.service.CommentPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment-post")
public class CommentPostController {
    private final CommentPostService commentPostService;

    @PostMapping("/create")
    //("[Tạo mới]")
    public ApiResponse<CommentPostCreateSdo> create(
            CommentPostCreateSdi req
    ) throws IOException {
        var rs = commentPostService.create(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/search")
    //("[Tìm kiếm]")
    public ApiResponse<List<CommentPostSearchSdo>> search(
            CommentPostSearchSdi req
    ) {
        var rs = commentPostService.search(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/self")
    //("[chi tiết]")
    public ApiResponse<CommentPostSelfSdo> self(
            CommentPostSelfSdi req
    ) {
        var rs = commentPostService.self(req);
        return new ApiResponse(rs);
    }


    @PostMapping("/update")
    //("[Cập nhập]")
    public ApiResponse<CommentPostUpdateSdo> update(
            CommentPostUpdateSdi req
    ) throws IOException {
        var rs = commentPostService.update(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
    //("[Xóa]")
    public ApiResponse<CommentPostDeleteSdo> delete(
             CommentPostDeleteSdi req
    ) {
        var rs = commentPostService.delete(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/like")
    public ApiResponse<CommentPostLikeSdo> like(
             CommentPostLikeSdi req
    ){
        var rs = commentPostService.like(req);
        return new ApiResponse<>(rs);
    }

    @PostMapping("/unlike")
    public ApiResponse<CommentPostUnlikeSdo> like(
            CommentPostUnlikeSdi req
    ){
        var rs = commentPostService.unlike(req);
        return new ApiResponse<>(rs);
    }
}
