package com.green.repository;

import com.green.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface StatusRepo extends JpaRepository<Status, Long>, StatusRepoCustom {
    @Query(value = "select * from status where  id = :id and status <>2 ", nativeQuery = true)
    Optional<Status> findById(Long id);

    @Query(value = "select * from status where  user_id = :userId and status <>2 ", nativeQuery = true)
    List<Status> findByUserId(Long userId);

}
