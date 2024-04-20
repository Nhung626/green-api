package com.green.repository;

import com.green.dto.comment.sdi.CommentSearchSdi;
import com.green.dto.comment.sdo.CommentSearchSdo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepoCustom {
    List<CommentSearchSdo> search(CommentSearchSdi req, Long userId);
}