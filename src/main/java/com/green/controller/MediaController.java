package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.media.sdi.MediaDeleteSdi;
import com.green.dto.media.sdi.MediaSelfSdi;
import com.green.dto.media.sdo.MediaUploadSdo;
import com.green.exception.AppException;
import com.green.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.green.constants.LabelKey.ERROR_NOT_EXIST;
import static com.green.constants.LabelKey.LABEL_FILE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/media")
//@Api(tags = "FILES", description = "QUAN LY FILE")
public class MediaController {
    private final MediaService mediaService;

    @PostMapping("/upload")
//  ("[tải lên]")
    public ApiResponse<MediaUploadSdo> uploadFile(
            @RequestPart("file") MultipartFile req) throws IOException {
        var rs = mediaService.uploadFile(req);
        return new ApiResponse(rs);

    }

    @GetMapping("/self")
//  ("[Chi tiết]")
    public ResponseEntity<Resource> self(MediaSelfSdi req) throws IOException {
        var media = mediaService.self(req);
        Path path = Paths.get(media.getPath());
        Resource resource = new UrlResource(path.toUri());
        if (resource.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.valueOf(media.getType()).toString())
                    .body(resource);
        } else {
            throw new AppException(ERROR_NOT_EXIST, List.of(LABEL_FILE, req.getId()));
        }
    }

    @PostMapping("/delete")
//  ("[Xoa]")
    public ApiResponse<List<MediaUploadSdo>> delete(
            @RequestBody MediaDeleteSdi req) {
        var rs = mediaService.delete(req);
        return new ApiResponse(rs);
    }
}
