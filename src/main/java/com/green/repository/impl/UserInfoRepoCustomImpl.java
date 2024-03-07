package com.green.repository.impl;

import com.green.dto.common.pagination.PageInfo;
import com.green.dto.user.sdo.UserSearchSdo;
import com.green.dto.userinfo.sdi.UserInfoSearchSdi;
import com.green.repository.UserInfoRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
@RequiredArgsConstructor
public class UserInfoRepoCustomImpl implements UserInfoRepoCustom {
    private final QueryRepo queryRepo;
}
