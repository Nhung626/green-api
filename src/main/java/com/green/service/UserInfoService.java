package com.green.service;

import com.green.dto.userinfo.sdi.*;
import com.green.dto.userinfo.sdo.*;

public interface UserInfoService {
    UserInfoCreateSdo create(UserInfoCreateSdi req);
    UserInfoUpdateSdo update(UserInfoUpdateSdi req);
    UserInfoSearchSdo search(UserInfoSearchSdi req);
    UserInfoDeleteSdo delete(UserInfoDeleteSdi req);
    UserInfoSelfSdo self(UserInfoSelfSdi req);
}
