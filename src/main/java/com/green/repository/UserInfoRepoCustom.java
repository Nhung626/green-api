package com.green.repository;

import com.green.dto.userinfo.sdi.UserInfoSearchSdi;
import com.green.dto.userinfo.sdo.UserInfoSearchSdo;

import java.util.List;

public interface UserInfoRepoCustom {
    UserInfoSearchSdo search(UserInfoSearchSdi request, Long myId);
}
