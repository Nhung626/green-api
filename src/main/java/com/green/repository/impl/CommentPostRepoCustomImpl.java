package com.green.repository.impl;

import com.green.dto.commentpost.sdi.CommentPostSearchSdi;
import com.green.dto.commentpost.sdo.CommentPostSearchSdo;
import com.green.repository.CommentPostRepoCustom;
import com.green.repository.common.QueryRepo;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class CommentPostRepoCustomImpl implements CommentPostRepoCustom {
    private final QueryRepo queryRepo;

    @Override
    public List<CommentPostSearchSdo> search(CommentPostSearchSdi req, Long userId) {
        var postId = req.getPostId();

        Map<String, Object> queryParams = new HashMap<>();
//        queryParams.put("postId", postId);
        if (userId != null) {
            queryParams.put("userId", userId);
        }

//        String sqlCountAll = "select distinct count(1) over ()";
//        String sqlGetData = ;

        StringBuilder sqlConditional = new StringBuilder("select cp.id, cp.parent_id, cp.user_id, gf.name as user_name, cp.post_id, cp.content, count(cp_child.id) as countReply, cp.created_at, " +
                "(select count(*) from like_comment_post lcp where lcp.comment_post_id = cp.id) as countLike " +
                (userId != null ? ", exists(select 1 from like_comment_post lcp where lcp.comment_post_id = cp.id and lcp.user_id = :userId)" : ", false") + " as userLiked ");
        sqlConditional.append(" from comment_post cp ")
                .append(" left join comment_post cp_child on cp.id = cp_child.parent_id and cp_child.status <> 2 ")
                .append(" inner join user u on cp.user_id = u.id and u.status <> 2 ")
                .append(" inner join garden_info gf on cp.user_id = gf.user_id and gf.status <> 2 ");

        if (!Objects.isNull(postId)) {
            sqlConditional.append(" where cp.post_id =:postId ");
            queryParams.put("postId", postId);
        }

        if (Objects.isNull(req.getParentId())) {
            sqlConditional.append(" and cp.parent_id is null ");
        } else {
            sqlConditional.append(" and cp.parent_id =:parentId ");
            queryParams.put("parentId", req.getParentId());
        }
        sqlConditional.append(" group by cp.id, cp.parent_id, cp.user_id, gf.name, cp.post_id ,cp.content ");
        sqlConditional.append("order by cp.id asc ");

        return queryRepo.queryList(
                sqlConditional.toString(),
                queryParams, CommentPostSearchSdo.class
        );
    }
}
