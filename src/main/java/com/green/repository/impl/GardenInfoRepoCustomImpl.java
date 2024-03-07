package com.green.repository.impl;

import com.green.dto.common.pagination.PageInfo;
import com.green.dto.gardeninfo.sdi.GardenInfoSearchSdi;
import com.green.dto.gardeninfo.sdo.GardenInfoSearchSdo;
import com.green.dto.user.sdo.UserSearchSdo;
import com.green.repository.GardenInfoRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

import static com.green.utils.DataUtil.isNullObject;
import static com.green.utils.DataUtil.toLikeConditional;

@RequiredArgsConstructor
public class GardenInfoRepoCustomImpl implements GardenInfoRepoCustom {
    private final QueryRepo queryRepo;

    @Override
    public Page<GardenInfoSearchSdo> search(GardenInfoSearchSdi request, PageInfo pageInfo) {
        var userId = request.getUserId();
        var name = request.getName();

        Map<String, Object> queryParams = new HashMap<>();

        String sqlCountAll = "select count(1) ";

        String sqlGetData = "select id, user_id, name, cover_id, description, status, created_at, created_by, updated_at, updated_by  ";

        StringBuilder sqlConditional = new StringBuilder();
        sqlConditional.append("from garden_info ");
        sqlConditional.append("where status <> 2 ");

        if (!isNullObject(userId)) {
            sqlConditional.append(" and user_id like :userId ");
            queryParams.put("userId", userId);
        }

        if (!isNullObject(name)) {
            sqlConditional.append(" and name like :name ");
            queryParams.put("name", name);
        }

        String sqlSort = "order by id asc ";

        return queryRepo.queryPage(
                sqlCountAll, sqlGetData, sqlConditional.toString(), sqlSort,
                queryParams, GardenInfoSearchSdo.class, pageInfo
        );
    }
}
