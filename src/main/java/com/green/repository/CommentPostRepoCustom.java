package com.green.repository;


import com.green.dto.commentpost.sdi.CommentPostSearchSdi;
import com.green.dto.commentpost.sdo.CommentPostSearchSdo;
import java.util.List;

public interface CommentPostRepoCustom {
    List<CommentPostSearchSdo> search(CommentPostSearchSdi req, Long userId);
}
