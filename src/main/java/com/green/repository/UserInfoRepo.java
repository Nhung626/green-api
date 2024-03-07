package com.green.repository;

import com.green.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> , UserInfoRepoCustom{
    @Query(value = "select * from user_info where  id = :id and status <>2 ", nativeQuery = true)
    Optional<UserInfo> findById(Long id);

    @Query(value = "select * from user_info where  userId = :id and status <>2 ", nativeQuery = true)
    Optional<UserInfo> findByUserId(Long id);
}
