package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.status.sdi.StatusLikeSdi;
import com.green.dto.status.sdi.StatusUnlikeSdi;
import com.green.dto.status.sdo.StatusLikeSdo;
import com.green.dto.status.sdo.StatusUnlikeSdo;
import com.green.dto.userinfo.sdi.*;
import com.green.dto.userinfo.sdo.*;
import com.green.service.UserInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/user-info")
@RequiredArgsConstructor
//@Api(tags = "USER INFO", description = "THÔNG TIN CƠ BẢN VÀ GIỚI THIỆU")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @PostMapping(path = "/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//  ("[Tạo mới]")
    public ApiResponse<UserInfoCreateSdo> create(
            @ModelAttribute UserInfoCreateSdi req
    ) throws IOException {
        var rs = userInfoService.create(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/search")
//  ("[Tìm kiếm]")
    public ApiResponse<UserInfoSearchSdo> search(
            @RequestBody @Valid UserInfoSearchSdi req
    ) {
        var rs = userInfoService.search(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/self")
//  ("[chi tiết]")
    public ApiResponse<UserInfoSelfSdo> self(
            @RequestBody UserInfoSelfSdi req
    ) {
        var rs = userInfoService.self(req);
        return new ApiResponse(rs);
    }


    @PostMapping(path = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//  ("[Cập nhập]")
    public ApiResponse<UserInfoUpdateSdo> update(
            UserInfoUpdateSdi req
    ) throws IOException {
        var rs = userInfoService.update(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/update-avatar")
//  ("[Cập nhập avatar]")
    public ApiResponse<UserAvatarUpdateSdo> update(
            UserAvatarUpdateSdi req
    ) throws IOException {
        var rs = userInfoService.uploadAvatar(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
//  ("[Xóa]")
    public ApiResponse<UserInfoDeleteSdo> delete(
            @RequestBody @Valid UserInfoDeleteSdi req
    ) {
        var rs = userInfoService.delete(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/follow")
    public ApiResponse<UserFollowSdo> like(
            UserFollowSdi req
    ){
        var rs = userInfoService.follow(req);
        return new ApiResponse<>(rs);
    }

    @PostMapping("/unfollow")
    public ApiResponse<UserUnfollowSdo> like(
            UserUnfollowSdi req
    ){
        var rs = userInfoService.unfollow(req);
        return new ApiResponse<>(rs);
    }
}
