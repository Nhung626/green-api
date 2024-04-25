package com.green.service;

import com.green.dto.commentpost.sdi.*;
import com.green.dto.commentpost.sdo.*;

import java.util.List;

public interface CommentPostService {
    CommentPostCreateSdo create(CommentPostCreateSdi req);

    CommentPostUpdateSdo update(CommentPostUpdateSdi req);

    CommentPostDeleteSdo delete(CommentPostDeleteSdi req);

    List<CommentPostSearchSdo> search(CommentPostSearchSdi req);

    CommentPostSelfSdo self(CommentPostSelfSdi req);

    CommentPostLikeSdo like(CommentPostLikeSdi req);

    CommentPostUnlikeSdo unlike(CommentPostUnlikeSdi req);
}
