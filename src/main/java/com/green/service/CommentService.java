package com.green.service;

import com.green.dto.comment.sdi.*;
import com.green.dto.comment.sdo.*;
import com.green.dto.common.pagination.PageInfo;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface CommentService {
    CommentCreateSdo create(CommentCreateSdi req) throws IOException;

    CommentUpdateSdo update(CommentUpdateSdi req);

    CommentDeleteSdo delete(CommentDeleteSdi req);

    Page<CommentSearchSdo> search(CommentSearchSdi req, PageInfo pageInfo);

    CommentSelfSdo self(CommentSelfSdi req);

    CommentLikeSdo like(CommentLikeSdi req);

    CommentUnlikeSdo unlike(CommentUnlikeSdi req);
}
