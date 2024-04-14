package com.green.repository;

import com.green.model.Land;
import com.green.model.Land;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LandRepo extends JpaRepository<Land, Long>, LandRepoCustom {
    @Query(value = "select * from land where  id = :id and status <>2 ", nativeQuery = true)
    Optional<Land> findById(Long id);

    @Query(value = "select * from land where  user_id = :id and status <>2 ", nativeQuery = true)
    Optional<Land> findByUserId(Long id);

    @Query(value = "select * from land where  name = :name and status <>2 ", nativeQuery = true)
    Optional<Land> findByName(String name);
}
