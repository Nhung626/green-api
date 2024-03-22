package com.green.repository;


import com.green.dto.commentpost.sdi.CommentPostSearchSdi;
import com.green.dto.commentpost.sdo.CommentPostSearchSdo;
import com.green.dto.common.pagination.PageInfo;
import org.springframework.data.domain.Page;

public interface CommentPostRepoCustom {
    Page<CommentPostSearchSdo> search(CommentPostSearchSdi req, PageInfo pageInfo, Long userId);
}
