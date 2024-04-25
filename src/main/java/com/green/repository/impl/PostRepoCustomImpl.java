package com.green.repository.impl;

import com.green.dto.post.sdi.PostSearchSdi;
import com.green.dto.post.sdi.PostSelfSdi;
import com.green.dto.post.sdo.PostSearchSdo;
import com.green.dto.post.sdo.PostSelfSdo;
import com.green.dto.status.sdo.StatusSearchSdo;
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
    public List<PostSearchSdo> search(PostSearchSdi req, Long myId) {
        var keyword = req.getKeyword();

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("userId", myId);

        StringBuilder sql = new StringBuilder("select p.*, " +
                "(select count(*) from like_post where like_post.post_id = p.id) as count_like,  " +
                "(select count(*) from save_post where save_post.post_id = p.id) as count_save " +
                (myId != null ? ", exists(select 1 from like_post lp where lp.post_id = p.id and lp.user_id = :userId)" : ", false") + " as userLiked " +
                (myId != null ? ", exists(select 1 from save_post where save_post.post_id = p.id and save_post.user_id = :userId)" : ", false") + " as userSaved " +
                " from post p where  p.status <>2 ");

        if (keyword != null) {
            sql.append(" AND p.title LIKE :keyword");
            queryParams.put("keyword", "%" + keyword + "%");
        }

        return queryRepo.queryList(sql.toString(), queryParams, PostSearchSdo.class);
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
                "(select count(*) from like_post where like_post.post_id = p.id) as count_like,  " +
                "(select count(*) from save_post where save_post.post_id = p.id) as count_save " +
                (userId != null ? ", exists(select 1 from like_post lp where lp.post_id =:id and lp.user_id = :userId)" : ", false") + " as userLiked " +
                (userId != null ? ", exists(select 1 from save_post where save_post.post_id =:id and save_post.user_id = :userId)" : ", false") + " as userSaved " +
                " from post p where  id = :id  and status <>2 ";

        return queryRepo.query(sql, queryParams, PostSelfSdo.class);
    }
}
