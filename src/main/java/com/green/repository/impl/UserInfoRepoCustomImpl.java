package com.green.repository.impl;

import com.green.dto.status.sdo.StatusSelfSdo;
import com.green.dto.userinfo.sdi.UserInfoSearchSdi;
import com.green.dto.userinfo.sdo.UserInfoSearchSdo;
import com.green.repository.UserInfoRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class UserInfoRepoCustomImpl implements UserInfoRepoCustom {
    private final QueryRepo queryRepo;

    @Override
    public UserInfoSearchSdo search(UserInfoSearchSdi req, Long myId) {
        var userId = req.getUserId();

        Map<String, Object> queryParams = new HashMap<>();
        if (userId != null) {
            queryParams.put("userId", userId);
        }
        if (userId != null) {
            queryParams.put("myId", myId);
        }

        String sql = "select uf.*, " +
                (myId != null ? " exists(select 1 from follow where follow.user_id = :myId and follow.user_follow_id = :userId)" : ", false") + " as isFollow " +
                " from user_info uf where  uf.user_id = :userId  and uf.status <>2 ";

        return queryRepo.query(sql, queryParams, UserInfoSearchSdo.class);
    }
}
