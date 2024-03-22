package com.green.repository;

import com.green.dto.common.pagination.PageInfo;
import com.green.dto.user.sdi.UserSearchSdi;
import com.green.dto.user.sdo.UserSearchSdo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserRepoCustom {
    List<UserSearchSdo> search(UserSearchSdi request);
}
