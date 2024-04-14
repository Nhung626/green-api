package com.green.repository;

import com.green.dto.user.sdo.UserSearchSdo;
import com.green.dto.userinfo.sdi.UserInfoSearchSdi;

import java.util.List;

public interface UserInfoRepoCustom {
    List<UserSearchSdo> search(UserInfoSearchSdi request);
}
