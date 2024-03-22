package com.green.repository;

import com.green.model.Like;
import com.green.model.LikeCmtPost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LikeCommentPostRepo extends JpaRepository<LikeCmtPost, Long> {
    Optional<LikeCmtPost> findByUserIdAndCommentId(Long userId, Long commentId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM like_comment_post WHERE user_id = :userId AND comment_id = :commentId", nativeQuery = true)
    void deleteLike(@Param("userId") Long userId, @Param("commentId") Long commentId);
}
