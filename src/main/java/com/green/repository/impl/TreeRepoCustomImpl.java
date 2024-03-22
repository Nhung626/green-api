package com.green.repository.impl;

import com.green.dto.land.sdo.LandSearchSdo;
import com.green.dto.tree.sdi.TreeSearchSdi;
import com.green.dto.tree.sdo.TreeSearchSdo;
import com.green.repository.TreeRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.green.utils.DataUtil.isNullObject;

@RequiredArgsConstructor
public class TreeRepoCustomImpl implements TreeRepoCustom {
    private final QueryRepo queryRepo;

    @Override
    public List<TreeSearchSdo> search(TreeSearchSdi req) {
        Long landId = req.getLandId();
        Long gardenId = req.getGardenId();

        Map<String, Object> queryParams = new HashMap<>();

        StringBuilder query = new StringBuilder("select id, name, type, start_date, end_date, img_id, garden_id, land_id " +
                "from tree where status <> 2 ");

        if (!isNullObject(landId)) {
            query.append(" and land_id like :landId ");
            queryParams.put("landId", landId);
        }
        if (!isNullObject(gardenId)) {
            query.append(" and garden_id like :gardenId ");
            queryParams.put("gardenId", gardenId);
        }

        String sqlSort = "order by id asc ";

        return queryRepo.queryList(query.toString(),
                queryParams, TreeSearchSdo.class
        );
    }
}
