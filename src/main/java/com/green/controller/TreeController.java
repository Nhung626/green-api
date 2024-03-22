package com.green.controller;


import com.green.dto.common.ApiResponse;
import com.green.dto.tree.sdi.*;
import com.green.dto.tree.sdo.*;
import com.green.service.TreeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tree")
public class TreeController {

    private final TreeService treeService;
    @PostMapping("/create")
    //("[Tạo mới]")
    public ApiResponse<TreeCreateSdo> create(
            TreeCreateSdi req
    ) throws IOException {
        var rs = treeService.create(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/search")
    //("[Tìm kiếm]")
    public ApiResponse<List<TreeSearchSdo>> search(
            TreeSearchSdi req
    ){
        var rs = treeService.search(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/self")
    //("[chi tiết]")
    public ApiResponse<TreeSelfSdo> self(
            TreeSelfSdi req
    ){
        var rs = treeService.self(req);
        return new ApiResponse(rs);
    }


    @PostMapping("/update")
    //("[Cập nhập]")
    public ApiResponse<TreeUpdateSdo> update(
            TreeUpdateSdi req
    ) throws IOException {
        var rs = treeService.update(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
    //("[Xóa]")
    public ApiResponse<TreeDeleteSdo> delete(
            @RequestBody @Valid TreeDeleteSdi req
    ) {
        var rs = treeService.delete(req);
        return new ApiResponse(rs);
    }
}
