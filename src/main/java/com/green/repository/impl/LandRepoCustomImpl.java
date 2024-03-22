package com.green.repository.impl;

import com.green.dto.land.sdi.LandSearchSdi;
import com.green.dto.land.sdo.LandSearchSdo;
import com.green.repository.LandRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.green.utils.DataUtil.isNullObject;

@RequiredArgsConstructor
public class LandRepoCustomImpl implements LandRepoCustom {
    private final QueryRepo queryRepo;
    @Override
    public List<LandSearchSdo> search(LandSearchSdi request) {
        var userId = request.getUserId();

        Map<String, Object> queryParams = new HashMap<>();

        StringBuilder query = new StringBuilder("select id, name, address, width, length, img_id, garden_id, user_id " +
                "from land where status <> 2 ");

        if (!isNullObject(userId)) {
            query.append(" and user_id like :userId ");
            queryParams.put("userId", userId);
        }

        query.append("order by id asc ");

        return queryRepo.queryList(query.toString(),
                queryParams, LandSearchSdo.class
        );
    }
}
