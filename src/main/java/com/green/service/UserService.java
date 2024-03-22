package com.green.service;

import com.green.dto.common.pagination.PageInfo;
import com.green.dto.user.sdi.*;
import com.green.dto.user.sdo.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserSearchSdo> search(UserSearchSdi request);

    UserSelfSdo self(UserSelfSdi req);

    UserRegisterSdo register(UserRegisterSdi req);

    UserResetPasswordSdo resetPassword(UserResetPasswordSdi req);

    UserChangePasswordSdo changePassword(UserChangePasswordSdi req);

    UserUpdateSdo update(UserUpdateSdi req);

    UserDeleteSdo delete(UserDeleteSdi req);
}
