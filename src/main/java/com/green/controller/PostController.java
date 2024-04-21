package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.post.sdi.*;
import com.green.dto.post.sdo.*;
import com.green.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Controller
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    //("[Tạo mới]")
    public ApiResponse<PostCreateSdo> create(
            PostCreateSdi req
    ) throws IOException {
        var rs = postService.create(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/search")
    //("[Tìm kiếm]")
    public ApiResponse<List<PostSearchSdo>> search(
            PostSearchSdi req
    ) {
        var rs = postService.search(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/self")
    //("[chi tiết]")
    public ApiResponse<PostSelfSdo> self(
            PostSelfSdi req
    ) {
        var rs = postService.self(req);
        return new ApiResponse(rs);
    }


    @PostMapping("/update")
    //("[Cập nhập]")
    public ApiResponse<PostUpdateSdo> update(
            PostUpdateSdi req
    ) throws IOException {
        var rs = postService.update(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
    //("[Xóa]")
    public ApiResponse<PostDeleteSdo> delete(
            @RequestBody @Valid PostDeleteSdi req
    ) {
        var rs = postService.delete(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/like")
    public ApiResponse<PostLikeSdo> like(
             PostLikeSdi req
    ){
        var rs = postService.like(req);
        return new ApiResponse<>(rs);
    }

    @PostMapping("/unlike")
    public ApiResponse<PostUnlikeSdo> like(
             PostUnlikeSdi req
    ){
        var rs = postService.unlike(req);
        return new ApiResponse<>(rs);
    }
}
