package com.green.repository;

import com.green.dto.comment.sdi.CommentSearchSdi;
import com.green.dto.comment.sdo.CommentSearchSdo;
import com.green.dto.common.pagination.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepoCustom {
    Page<CommentSearchSdo> search(CommentSearchSdi req, PageInfo pageInfo, Long userId);
}