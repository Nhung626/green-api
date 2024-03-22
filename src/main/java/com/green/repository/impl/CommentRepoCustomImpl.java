package com.green.repository.impl;

import com.green.dto.comment.sdi.CommentSearchSdi;
import com.green.dto.comment.sdo.CommentSearchSdo;
import com.green.dto.common.pagination.PageInfo;
import com.green.repository.CommentRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class CommentRepoCustomImpl implements CommentRepoCustom {
    private final QueryRepo queryRepo;

    @Override
    public Page<CommentSearchSdo> search(CommentSearchSdi req, PageInfo pageInfo, Long userId) {
        var statusId = req.getStatusId();

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("statusId", statusId);
        if (userId != null) {
            queryParams.put("userId", userId);
        }

        String sqlCountAll = "select distinct count(1) over ()";
        String sqlGetData = "select c.id, c.parent_id, c.user_id, uf.name as user_name, c.status_id, c.status, c.content, count(c_child.id) as countReply, " +
                "(select count(*) from like_comment lc where lc.comment_id = c.id) as countLike " +
                (userId != null ? ", exists(select 1 from lc where lc.comment_id = c.id and lc.user_id = :userId)" : ", false") + " as userLiked ";

        StringBuilder sqlConditional = new StringBuilder();
        sqlConditional.append(" from comment c ")
                .append(" left join comment c_child on c.id = c_child.parent_id and c_child.status <> 2 ")
                .append(" inner join user u on c.user_id = u.id and u.status <> 2 ")
                .append(" inner join user_info uf on c.user_id = uf.user_id and uf.status <> 2 ");

        if(!Objects.isNull(statusId)){
            sqlConditional.append(" where c.status_id =:statusId ");
            queryParams.put("statusId", statusId);
        }

        if(Objects.isNull(req.getParentId())){
            sqlConditional.append(" and c.parent_id is null ");
        }else{
            sqlConditional.append(" and c.parent_id =:parentId ");
            queryParams.put("parentId", req.getParentId());
        }
        sqlConditional.append(" group by c.id, c.parent_id, c.user_id, uf.name, c.status_id, c.status ,c.content ");
        String sqlSort = "order by c.id asc ";

        return queryRepo.queryPage(
                sqlCountAll, sqlGetData, sqlConditional.toString(), sqlSort,
                queryParams, CommentSearchSdo.class, pageInfo
        );
    }
}