package com.green.repository;

import com.green.model.Like;
import com.green.model.LikePost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikePostRepo extends JpaRepository<LikePost, Long> {
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM likes WHERE user_id = :userId AND post_id = :postId", nativeQuery = true)
    void deleteLike(@Param("userId") Long userId, @Param("postId") Long postId);
}
