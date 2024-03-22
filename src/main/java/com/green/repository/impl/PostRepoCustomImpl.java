package com.green.repository.impl;

import com.green.dto.post.sdi.PostSearchSdi;
import com.green.dto.post.sdi.PostSelfSdi;
import com.green.dto.post.sdo.PostSearchSdo;
import com.green.dto.post.sdo.PostSelfSdo;
import com.green.dto.status.sdo.StatusSelfSdo;
import com.green.repository.PostRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PostRepoCustomImpl implements PostRepoCustom {
    private final QueryRepo queryRepo;
    @Override
    public List<PostSearchSdo> search(PostSearchSdi req) {
        return null;
    }

    @Override
    public PostSelfSdo self(PostSelfSdi req, Long userId) {
        var id = req.getId();

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", id);
        if (userId != null) {
            queryParams.put("userId", userId);
        }

        String sql = "select p.*, " +
                "(select count(*) from like_post where l.status_id = p.id) as countLike " +
                "(select count(*) from save where save.status_id = p.id) as countSave " +
                (userId != null ? ", exists(select 1 from lp where lp.post_id =:id and lp.user_id = :userId)" : ", false") + " as userLiked " +
                (userId != null ? ", exists(select 1 from save where save.post_id =:id and lp.user_id = :userId)" : ", false") + " as userSaved " +
                " from post p where  id = :id  and status <>2 ";

        return queryRepo.query(sql, queryParams, PostSelfSdo.class);
    }
}
