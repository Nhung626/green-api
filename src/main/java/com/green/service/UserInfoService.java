package com.green.service;

import com.green.dto.userinfo.sdi.UserUnfollowSdi;
import com.green.dto.userinfo.sdo.UserFollowSdo;
import com.green.dto.userinfo.sdo.UserUnfollowSdo;
import com.green.dto.userinfo.sdi.*;
import com.green.dto.userinfo.sdo.*;

import java.io.IOException;

public interface UserInfoService {
    UserInfoCreateSdo create(UserInfoCreateSdi req) throws IOException;
    UserInfoUpdateSdo update(UserInfoUpdateSdi req) throws IOException;
    UserInfoSearchSdo search(UserInfoSearchSdi req);
    UserInfoDeleteSdo delete(UserInfoDeleteSdi req);
    UserInfoSelfSdo self(UserInfoSelfSdi req);
    UserAvatarUpdateSdo uploadAvatar(UserAvatarUpdateSdi req) throws IOException;

    UserFollowSdo follow(UserFollowSdi req);

    UserUnfollowSdo unfollow(UserUnfollowSdi req);
}
