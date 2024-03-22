package com.green.repository.impl;

import com.green.dto.diary.sdi.DiarySearchSdi;
import com.green.dto.diary.sdo.DiarySearchSdo;
import com.green.repository.DiaryRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.green.utils.DataUtil.isNullObject;

@RequiredArgsConstructor
public class DiaryRepoCustomImpl implements DiaryRepoCustom {
    private final QueryRepo queryRepo;

    @Override
    public List<DiarySearchSdo> search(DiarySearchSdi req) {
        var landId = req.getLandId();
        var treeId = req.getTreeId();

        Map<String, Object> queryParams = new HashMap<>();

        StringBuilder query = new StringBuilder("select id, description, img_id_1, img_id_2, img_id_3, land_id, tree_id " +
                "from diary where status <> 2 ");

        if (!isNullObject(landId)) {
            query.append(" and land_id like :landId ");
            queryParams.put("landId", landId);
        }

        if (!isNullObject(treeId)) {
            query.append(" and tree_id like :treeId ");
            queryParams.put("treeId", treeId);
        }

        String sqlSort = "order by id asc ";

        return queryRepo.queryList(query.toString(),
                queryParams, DiarySearchSdo.class
        );
    }
}
