package com.green.repository;

import com.green.model.GardenInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GardenInfoRepo extends JpaRepository<GardenInfo, Long>, GardenInfoRepoCustom {
    @Query(value = "select * from garden_info where  id = :id and status <>2 ", nativeQuery = true)
    Optional<GardenInfo> findById(Long id);

    @Query(value = "select * from garden_info where  userId = :id and status <>2 ", nativeQuery = true)
    Optional<GardenInfo> findByUserId(Long id);
}
