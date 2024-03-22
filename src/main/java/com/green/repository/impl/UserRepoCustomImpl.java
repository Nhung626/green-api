package com.green.repository.impl;

import com.green.dto.user.sdi.UserSearchSdi;
import com.green.dto.user.sdo.UserSearchSdo;
import com.green.repository.UserRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static com.green.utils.DataUtil.isNullObject;
import static com.green.utils.DataUtil.toLikeConditional;

@RequiredArgsConstructor
public class UserRepoCustomImpl implements UserRepoCustom {
    private final QueryRepo queryRepo;

    @Override
    public List<UserSearchSdo> search(UserSearchSdi request) {
        var email = request.getEmail();
        Map<String, Object> queryParams = new HashMap<>();

        StringBuilder query = new StringBuilder("select id, email, status, created_at, created_by, updated_at, updated_by " +
                "from user where status <> 2 ");
        if (!isNullObject(email)) {
            query.append(" and email like :email ");
            queryParams.put("email", toLikeConditional(email));
        }

        query.append("order by id asc ");

        return queryRepo.queryList(query.toString(),
                queryParams, UserSearchSdo.class
        );
    }
}
