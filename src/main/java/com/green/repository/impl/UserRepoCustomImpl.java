package com.green.repository.impl;

import com.green.dto.common.pagination.PageInfo;
import com.green.dto.user.sdi.UserSearchSdi;
import com.green.dto.user.sdo.UserSearchSdo;
import com.green.repository.UserRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

import static com.green.utils.DataUtil.isNullObject;
import static com.green.utils.DataUtil.toLikeConditional;

@RequiredArgsConstructor
public class UserRepoCustomImpl implements UserRepoCustom {
    private final QueryRepo queryRepo;

    @Override
    public Page<UserSearchSdo> search(UserSearchSdi request, PageInfo pageInfo) {
        var email = request.getEmail();
        var name = request.getName();

        Map<String, Object> queryParams = new HashMap<>();

        String sqlCountAll = "select count(1) ";

        String sqlGetData = "select id, name, email, status, created_at, created_by, updated_at, updated_by  ";

        StringBuilder sqlConditional = new StringBuilder();
        sqlConditional.append("from users ");
        sqlConditional.append("where status <> 2 ");

        if (!isNullObject(email)) {
            sqlConditional.append(" and email like :email ");
            queryParams.put("email", toLikeConditional(email));
        }

        if (!isNullObject(name)) {
            sqlConditional.append(" and name ilike :name ");
            queryParams.put("name", toLikeConditional(name));
        }

        String sqlSort = "order by id asc ";

        return queryRepo.queryPage(
                sqlCountAll, sqlGetData, sqlConditional.toString(), sqlSort,
                queryParams, UserSearchSdo.class, pageInfo
        );
    }
}
