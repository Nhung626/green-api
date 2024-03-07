package com.green.service;

import com.green.dto.common.pagination.PageInfo;
import com.green.dto.user.sdi.*;
import com.green.dto.user.sdo.*;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserSearchSdo> search(UserSearchSdi req, PageInfo pageInfo);

    UserSelfSdo self(UserSelfSdi req);

    UserRegisterSdo register(UserRegisterSdi req);

    UserResetPasswordSdo resetPassword(UserResetPasswordSdi req);

    UserChangePasswordSdo changePassword(UserChangePasswordSdi req);

    UserUpdateSdo update(UserUpdateSdi req);

    UserDeleteSdo delete(UserDeleteSdi req);
}
