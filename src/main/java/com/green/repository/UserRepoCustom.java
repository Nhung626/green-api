package com.green.repository;

import com.green.dto.user.sdi.UserSearchSdi;
import com.green.dto.user.sdo.UserSearchSdo;

import java.util.List;

public interface UserRepoCustom {
    List<UserSearchSdo> search(UserSearchSdi request);
}
