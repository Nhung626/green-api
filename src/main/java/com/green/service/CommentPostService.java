package com.green.service;

import com.green.dto.commentpost.sdi.*;
import com.green.dto.commentpost.sdo.*;
import com.green.dto.common.pagination.PageInfo;
import org.springframework.data.domain.Page;

public interface CommentPostService {
    CommentPostCreateSdo create(CommentPostCreateSdi req);
    CommentPostUpdateSdo update(CommentPostUpdateSdi req);

    CommentPostDeleteSdo delete(CommentPostDeleteSdi req);
    Page<CommentPostSearchSdo> search(CommentPostSearchSdi req, PageInfo pageInfo);
    CommentPostSelfSdo self(CommentPostSelfSdi req);
    CommentPostLikeSdo like(CommentPostLikeSdi req);
    CommentPostUnlikeSdo unlike(CommentPostUnlikeSdi req);
}
