package com.green.repository;

import com.green.dto.post.sdi.PostSearchSdi;
import com.green.dto.post.sdi.PostSelfSdi;
import com.green.dto.post.sdo.PostSearchSdo;
import com.green.dto.post.sdo.PostSelfSdo;

import java.util.List;

public interface PostRepoCustom {
    List<PostSearchSdo> search (PostSearchSdi req);

    PostSelfSdo self(PostSelfSdi req, Long userId);
}
