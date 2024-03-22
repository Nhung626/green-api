package com.green.repository;

import com.green.model.Like;
import com.green.model.LikeComment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeCommentRepo extends JpaRepository<LikeComment, Long> {
    Optional<Like> findByUserIdAndCommentId(Long userId, Long commentId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM like_comment WHERE user_id = :userId AND comment_id = :commentId", nativeQuery = true)
    void deleteLike(@Param("userId") Long userId, @Param("commentId") Long commentId);
}
