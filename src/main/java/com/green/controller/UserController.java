package com.green.controller;

import com.green.dto.common.ApiResponse;
import com.green.dto.user.sdi.*;
import com.green.dto.user.sdo.*;
import com.green.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
//@Api(tags = "USER ACCOUNT", description = "QUẢN LÝ NGƯỜI DÙNG")
public class UserController {
    private final UserService userService;

    @GetMapping("/search")
    //Tìm kiếm
    public ApiResponse<List<UserSearchSdo>> search(
            UserSearchSdi req
    ) {
        var rs = userService.search(req);
        return new ApiResponse(rs);
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/self")
    //chi tiết
    public ApiResponse<UserSelfSdo> self(
            UserSelfSdi sdi
    ) {
        var rs = userService.self(sdi);
        return new ApiResponse(rs);
    }


    @PostMapping("/register")
    //Đăng ký tài khoản người dùng
    public ApiResponse<UserRegisterSdo> signup(
            @RequestBody @Valid UserRegisterSdi req
    ) {
        var rs = userService.register(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/update")
    //Cập nhập
    public ApiResponse<UserUpdateSdo> update(
            @RequestBody @Valid UserUpdateSdi sdi
    ) {
        var rs = userService.update(sdi);
        return new ApiResponse(rs);
    }

    @PostMapping("/reset-password")
    //Đặt lại mật khẩu
    public ApiResponse<UserResetPasswordSdo> resetPassword(
            @RequestBody @Valid UserResetPasswordSdi req
    ) {
        var rs = userService.resetPassword(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/change-password")
    //Đổi mật khẩu
    public ApiResponse<UserChangePasswordSdo> changePassword(
            @RequestBody @Valid UserChangePasswordSdi req
    ) {
        var rs = userService.changePassword(req);
        return new ApiResponse(rs);
    }

    @PostMapping("/delete")
    //Xóa
    public ApiResponse<UserDeleteSdo> delete(
            @RequestBody @Valid UserDeleteSdi sdi
    ) {
        var rs = userService.delete(sdi);
        return new ApiResponse(rs);
    }
}
