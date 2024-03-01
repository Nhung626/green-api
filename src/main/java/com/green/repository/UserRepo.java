package com.green.repository;

import com.green.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long>, UserRepoCustom{
    @Query(value = "select * from user where  email = :email and status = 1", nativeQuery = true)
    Optional<User> findByEmail(String email);
    @Query(value = "select * from user where  id = :id and status = 1", nativeQuery = true)
    Optional<User> findUserById(Long id);
}
