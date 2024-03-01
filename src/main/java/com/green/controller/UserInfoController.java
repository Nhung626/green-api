package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.userinfo.sdi.*;
import com.green.dto.userinfo.sdo.*;
import com.green.service.UserInfoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-info")
@AllArgsConstructor
//@Api(tags = "USER INFO", description = "THÔNG TIN CƠ BẢN VÀ GIỚI THIỆU")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @PostMapping("/create")
//    @ApiOperation("[Tạo mới]")
    public ApiResponse<UserInfoCreateSdo> create(
            @RequestBody @Valid UserInfoCreateSdi req
    ) {
        var rs = userInfoService.create(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/search")
//    @ApiOperation("[Tìm kiếm]")
    public ApiResponse<Page<UserInfoSearchSdo>> search(
            UserInfoSearchSdi req
    ){
        var rs = userInfoService.search(req);
        return new ApiResponse(rs);
    }

    @GetMapping("/self")
//    @ApiOperation("[chi tiết]")
    public ApiResponse<UserInfoSelfSdo> self(
            UserInfoSelfSdi req
    ){
        var rs = userInfoService.self(req);
        return new ApiResponse(rs);
    }


    @PostMapping("/update")
//    @ApiOperation("[Cập nhập]")
    public ApiResponse<UserInfoUpdateSdo> update(
            @RequestBody @Valid UserInfoUpdateSdi req
    ) {
        var rs = userInfoService.update(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
//    @ApiOperation("[Xóa]")
    public ApiResponse<UserInfoDeleteSdo> delete(
            @RequestBody @Valid UserInfoDeleteSdi req
    ) {
        var rs = userInfoService.delete(req);
        return new ApiResponse(rs);
    }
}
