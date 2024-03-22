package com.green.repository;

import com.green.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MediaRepo extends JpaRepository<Media, Long>, MediaRepoCustom {
    @Query(value = "select * from media where file_name like concat('%',:name,'%') and status <> 2", nativeQuery = true)
    List<Media> findAllByName(String name);

    @Query(value = "select * from media where id = :id and status <> 2", nativeQuery = true)
    Optional<Media> findById(Long id);

}
