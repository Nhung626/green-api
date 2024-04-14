package com.green.service;

import com.green.dto.user.sdi.*;
import com.green.dto.user.sdo.*;

import java.util.List;

public interface UserService {
    List<UserSearchSdo> search(UserSearchSdi request);

    UserSelfSdo self(UserSelfSdi req);

    UserRegisterSdo register(UserRegisterSdi req);

    UserResetPasswordSdo resetPassword(UserResetPasswordSdi req);

    UserChangePasswordSdo changePassword(UserChangePasswordSdi req);

    UserUpdateSdo update(UserUpdateSdi req);

    UserDeleteSdo delete(UserDeleteSdi req);

    UserFollowSdo follow(UserFollowSdi req);

    UserUnfollowSdo unfollow(UserUnfollowSdi req);
}
