package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.common.pagination.PageInfo;
import com.green.dto.user.sdi.*;
import com.green.dto.user.sdo.*;
import com.green.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
//@Api(tags = "USER ACCOUNT", description = "QUẢN LÝ NGƯỜI DÙNG")
public class UserController {
    private final UserService userService;

    @GetMapping("/search")
//    @ApiOperation("[Tìm kiếm]")
    public ApiResponse<UserSearchSdo> search(
            UserSearchSdi req, PageInfo pageInfo
    ) {
        var rs = userService.search(req, pageInfo);
        return new ApiResponse(rs);
    }

    @GetMapping("/self")
//    @ApiOperation("[chi tiết]")
    public ApiResponse<UserSelfSdo> self(
            UserSelfSdi sdi
    ) {
        var rs = userService.self(sdi);
        return new ApiResponse(rs);
    }

    @PostMapping("/create")
//    @ApiOperation("[Tạo mới]")
    public ApiResponse<UserCreateSdo> create(
            @RequestBody @Valid UserCreateSdi req
    ) {
        var rs = userService.create(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/signup")
//    @ApiOperation("[Đăng ký tài khoản người dùng]")
    public ApiResponse<UserCreateSdo> signup(
            @RequestBody @Valid UserRegisterSdi req
    ) {
        var rs = userService.register(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/update")
//    @ApiOperation("[Cập nhập]")
    public ApiResponse<UserUpdateSdo> update(
            @RequestBody @Valid UserUpdateSdi sdi
    ) {
        var rs = userService.update(sdi);
        return new ApiResponse(rs);
    }

    @PostMapping("/reset-password")
//    @ApiOperation("[Đặt lại mật khẩu]")
    public ApiResponse<UserResetPasswordSdo> resetPassword(
            @RequestBody @Valid UserResetPasswordSdi req
    ) {
        var rs = userService.resetPassword(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/change-password")
//    @ApiOperation("[Đổi mật khẩu]")
    public ApiResponse<UserChangePasswordSdo> changePassword(
            @RequestBody @Valid UserChangePasswordSdi req
    ) {
        var rs = userService.changePassword(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
//    @ApiOperation("[Xóa]")
    public ApiResponse<UserDeleteSdo> delete(
            @RequestBody @Valid UserDeleteSdi sdi
    ) {
        var rs = userService.delete(sdi);
        return new ApiResponse(rs);
    }
}
