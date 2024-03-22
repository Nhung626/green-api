package com.green.repository.impl;

import com.green.dto.comment.sdi.CommentSearchSdi;
import com.green.dto.comment.sdo.CommentSearchSdo;
import com.green.dto.commentpost.sdi.CommentPostSearchSdi;
import com.green.dto.commentpost.sdo.CommentPostSearchSdo;
import com.green.dto.common.pagination.PageInfo;
import com.green.repository.CommentPostRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class CommentPostRepoCustomImpl implements CommentPostRepoCustom {
    private final QueryRepo queryRepo;
    @Override
    public Page<CommentPostSearchSdo> search(CommentPostSearchSdi req, PageInfo pageInfo, Long userId) {
        var postId = req.getPostId();

        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("postId", postId);
        if (userId != null) {
            queryParams.put("userId", userId);
        }

        String sqlCountAll = "select distinct count(1) over ()";
        String sqlGetData = "select cp.id, cp.parent_id, cp.user_id, uf.name as user_name, cp.post_id, cp.content, count(cp_child.id) as countReply, " +
                "(select count(*) from like_comment_post lcp where lcp.comment_id = c.id) as countLike " +
                (userId != null ? ", exists(select 1 from lcp where lcp.comment_post_id = c.id and lcp.user_id = :userId)" : ", false") + " as userLiked ";

        StringBuilder sqlConditional = new StringBuilder();
        sqlConditional.append(" from comment_post cp ")
                .append(" left join comment_post cp_child on cp.id = cp_child.parent_id and c_child.post <> 2 ")
                .append(" inner join user u on cp.user_id = u.id and u.post <> 2 ")
                .append(" inner join user_info uf on cp.user_id = uf.user_id and uf.post <> 2 ");

        if(!Objects.isNull(postId)){
            sqlConditional.append(" where cp.post_id =:postId ");
            queryParams.put("postId", postId);
        }

        if(Objects.isNull(req.getParentId())){
            sqlConditional.append(" and cp.parent_id is null ");
        }else{
            sqlConditional.append(" and cp.parent_id =:parentId ");
            queryParams.put("parentId", req.getParentId());
        }
        sqlConditional.append(" group by cp.id, cp.parent_id, cp.user_id, uf.name, cp.post_id ,cp.content ");
        String sqlSort = "order by cp.id asc ";

        return queryRepo.queryPage(
                sqlCountAll, sqlGetData, sqlConditional.toString(), sqlSort,
                queryParams, CommentPostSearchSdo.class, pageInfo
        );
    }
}
