package com.green.repository;

import com.green.model.SavePost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SavePostRepo extends JpaRepository<SavePost, Long> {
    Optional<SavePost> findByUserIdAndPostId(Long userId, Long postId);

    @Query(value = "select * from save_post where  user_id = :userId and status <>2 ", nativeQuery = true)
    List<SavePost> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM save_post WHERE user_id = :userId AND status_id = :statusId", nativeQuery = true)
    void deleteLike(@Param("userId") Long userId, @Param("postId") Long statusId);

}
