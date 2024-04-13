package com.green.service;

import com.green.dto.post.sdi.*;
import com.green.dto.post.sdo.*;

import java.io.IOException;
import java.util.List;

public interface PostService {
    PostCreateSdo create(PostCreateSdi req) throws IOException;

    PostUpdateSdo update(PostUpdateSdi req) throws IOException;

    List<PostSearchSdo> search(PostSearchSdi req);

    PostDeleteSdo delete(PostDeleteSdi req);

    PostSelfSdo self(PostSelfSdi req, Long userId);

    PostLikeSdo like(PostLikeSdi req);

    PostUnlikeSdo unlike(PostUnlikeSdi req);

    PostSaveSdo save(PostSaveSdi req);

    PostUnSaveSdo unSave(PostUnsaveSdi req);
}
