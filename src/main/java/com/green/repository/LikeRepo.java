package com.green.repository;

import com.green.model.Like;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepo extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndStatusId(Long userId, Long statusId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM likes WHERE user_id = :userId AND status_id = :statusId", nativeQuery = true)
    void deleteLike(@Param("userId") Long userId, @Param("statusId") Long statusId);
}
