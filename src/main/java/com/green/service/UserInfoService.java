package com.green.service;

import com.green.dto.userinfo.sdi.*;
import com.green.dto.userinfo.sdo.*;

import java.io.IOException;

public interface UserInfoService {
    UserInfoCreateSdo create(UserInfoCreateSdi req) throws IOException;
    UserInfoUpdateSdo update(UserInfoUpdateSdi req);
    UserInfoSearchSdo search(UserInfoSearchSdi req);
    UserInfoDeleteSdo delete(UserInfoDeleteSdi req);
    UserInfoSelfSdo self(UserInfoSelfSdi req);
    UserAvataUpdateSdo uploadAvata(UserAvataUpdateSdi req) throws IOException;
}
