package com.green.repository.impl;

import com.green.dto.comment.sdi.CommentSearchSdi;
import com.green.dto.comment.sdo.CommentSearchSdo;
import com.green.repository.CommentRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class CommentRepoCustomImpl implements CommentRepoCustom {
    private final QueryRepo queryRepo;

    @Override
    public List<CommentSearchSdo> search(CommentSearchSdi req, Long userId) {
        var statusId = req.getStatusId();

        Map<String, Object> queryParams = new HashMap<>();
        if (userId != null) {
            queryParams.put("userId", userId);
        }

        StringBuilder sqlConditional = new StringBuilder("select c.id, c.parent_id, c.user_id, gf.name as user_name, c.status_id, c.status, c.content, count(c_child.id) as countReply, c.created_at, " +
                "(select count(*) from like_comment lc where lc.comment_id = c.id) as countLike " +
                (userId != null ? ", exists(select 1 from like_comment lc where lc.comment_id = c.id and lc.user_id = :userId)" : ", false") + " as userLiked ");
        sqlConditional.append(" from comment c ")
                .append(" left join comment c_child on c.id = c_child.parent_id and c_child.status <> 2 ")
                .append(" inner join user u on c.user_id = u.id and u.status <> 2 ")
                .append(" inner join garden_info gf on c.user_id = gf.user_id and gf.status <> 2 ")
                .append(" where c.status<>2 ");

        if (!Objects.isNull(statusId)) {
            sqlConditional.append(" and c.status_id =:statusId ");
            queryParams.put("statusId", statusId);
        }

        if (Objects.isNull(req.getParentId())) {
            sqlConditional.append(" and c.parent_id is null ");
        } else {
            sqlConditional.append(" and c.parent_id =:parentId ");
            queryParams.put("parentId", req.getParentId());
        }
        sqlConditional.append(" group by c.id, c.parent_id, c.user_id, gf.name, c.status_id, c.status ,c.content ");
        sqlConditional.append("order by c.id asc ");

        return queryRepo.queryList(sqlConditional.toString(),
                queryParams, CommentSearchSdo.class
        );
    }
}