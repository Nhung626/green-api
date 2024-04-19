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
    public List<StatusSearchSdo> search(StatusSearchSdi req, Long myId) {
        var keyword = req.getKeywords();
        var userId = req.getUserId();

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("myId", myId);

        StringBuilder sql = new StringBuilder("SELECT s.*, " +
                "(SELECT COUNT(*) FROM likes l WHERE l.status_id = s.id) AS countLike, " +
                (myId != null ? "EXISTS(SELECT 1 FROM likes WHERE likes.status_id = s.id AND likes.user_id = :myId) AS userLiked" : "false AS userLiked") +
                " FROM status s WHERE status <> 2");

        if (userId != null) {
            sql.append(" AND s.user_id = :userId");
            queryParams.put("userId", userId);
        }
        if (keyword != null) {
            sql.append(" AND s.content LIKE :keyword");
            queryParams.put("keyword", "%" + keyword + "%");
        }

        return queryRepo.queryList(sql.toString(), queryParams, StatusSearchSdo.class);
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
                "(select count(*) from likes l  where l.status_id = s.id) as countLike " +
                (userId != null ? ", exists(select 1 from likes where likes.status_id = s.id and likes.user_id = :userId)" : ", false") + " as userLiked " +
                " from status s where  id = :id  and status <>2 ";

        return queryRepo.query(sql, queryParams, StatusSelfSdo.class);
    }
}
