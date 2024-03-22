package com.green.repository.impl;

import com.green.dto.status.sdi.StatusSearchSdi;
import com.green.dto.status.sdi.StatusSelfSdi;
import com.green.dto.status.sdo.StatusSearchSdo;
import com.green.dto.status.sdo.StatusSelfSdo;
import com.green.repository.StatusRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class StatusRepoCustomImpl implements StatusRepoCustom {
    private final QueryRepo queryRepo;

    @Override
    public List<StatusSearchSdo> search(StatusSearchSdi req) {
        return null;
    }

    @Override
    public StatusSelfSdo self(StatusSelfSdi req, Long userId) {
        var id = req.getId();

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        if (userId != null) {
            queryParams.put("userId", userId);
        }

        String sql = "select s.*, " +
                "(select count(*) from like where l.status_id = s.id) as countLike " +
                (userId != null ? ", exists(select 1 from like where like.status_id =:id and like.user_id = :userId)" : ", false") + " as userLiked " +
                " from status s where  id = :id  and status <>2 ";

        return queryRepo.query(sql, queryParams, StatusSelfSdo.class);
    }
}
