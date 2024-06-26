package com.green.repository.impl;

import com.green.dto.gardeninfo.sdi.GardenInfoSearchSdi;
import com.green.dto.gardeninfo.sdo.GardenInfoSearchSdo;
import com.green.repository.GardenInfoRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.green.utils.DataUtil.isNullObject;

@RequiredArgsConstructor
public class GardenInfoRepoCustomImpl implements GardenInfoRepoCustom {
    private final QueryRepo queryRepo;

    @Override
    public List<GardenInfoSearchSdo> search(GardenInfoSearchSdi request) {
        var userId = request.getUserId();
        var name = request.getName();

        Map<String, Object> queryParams = new HashMap<>();

        StringBuilder query = new StringBuilder("select id, name, cover_id, user_id, description " +
                "from garden_info where status <> 2 ");

        if (!isNullObject(userId)) {
            query.append(" and user_id like :userId ");
            queryParams.put("userId", userId);
        }

        if (!isNullObject(name)) {
            query.append(" and name like :name ");
            queryParams.put("name", name);
        }

        query.append("order by id asc ");

        return queryRepo.queryList(query.toString(),
                queryParams, GardenInfoSearchSdo.class
        );
    }
}
