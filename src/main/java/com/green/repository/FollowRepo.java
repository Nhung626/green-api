package com.green.repository;

import com.green.model.Follow;
import com.green.model.Like;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FollowRepo extends JpaRepository<Follow, Long> {
    Optional<Follow> findByUserIdAndUserFollowId(Long userId, Long userFollowId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM follow WHERE user_id = :userId AND user_follow_id = :userFollowId", nativeQuery = true)
    void deleteFollow(@Param("userId") Long userId, @Param("userFollowId") Long userFollowId);
}
