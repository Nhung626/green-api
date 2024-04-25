package com.green.service;

import com.green.dto.comment.sdi.*;
import com.green.dto.comment.sdo.*;
import java.util.List;

import java.io.IOException;

public interface CommentService {
    CommentCreateSdo create(CommentCreateSdi req) throws IOException;

    CommentUpdateSdo update(CommentUpdateSdi req);

    CommentDeleteSdo delete(CommentDeleteSdi req);

    List<CommentSearchSdo> search(CommentSearchSdi req);

    CommentSelfSdo self(CommentSelfSdi req);

    CommentLikeSdo like(CommentLikeSdi req);

    CommentUnlikeSdo unlike(CommentUnlikeSdi req);
}
