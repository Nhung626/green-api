package com.green.repository;

import com.green.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long>, PostRepoCustom {
    @Query(value = "select * from post where  id = :id and status <>2 ", nativeQuery = true)
    Optional<Post> findById(Long id);
}
