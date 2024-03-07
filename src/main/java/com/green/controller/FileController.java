package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.file.sdi.FileDeleteSdi;
import com.green.dto.file.sdi.FileSearchSdi;
import com.green.dto.file.sdi.FileSelfSdi;
import com.green.dto.file.sdo.FileSearchSdo;
import com.green.dto.file.sdo.FileSelfSdo;
import com.green.dto.file.sdo.FileUploadSdo;
import com.green.service.FileService;
//import io.swagger.annotations.Api;
////import io.swagger.annotatio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
//@Api(tags = "FILES", description = "QUAN LY FILE")
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload")
//  ("[tải lên]")
    public ApiResponse<FileUploadSdo> uploadFile(
            @RequestPart("file") MultipartFile req, Boolean isTemp) {
        var rs = fileService.uploadFile(req, isTemp);
        return new ApiResponse(rs);

    }

    @GetMapping("/search")
//  ("[tìm kiếm]")
    public ApiResponse<List<FileSearchSdo>> search(FileSearchSdi req) {
        var rs = fileService.search(req);
        return new ApiResponse(rs);

    }

    @GetMapping("/self")
//  ("[Chi tiết]")
    public ApiResponse<FileSelfSdo> self(FileSelfSdi req) {
        var rs = fileService.self(req);
        return new ApiResponse(rs);

    }

    @PostMapping("/delete")
//  ("[Xoa]")
    public ApiResponse<List<FileUploadSdo>> delete(
            @RequestBody FileDeleteSdi req) {
        var rs = fileService.delete(req);
        return new ApiResponse(rs);
    }
}
