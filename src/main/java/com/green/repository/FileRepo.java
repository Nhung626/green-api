package com.green.repository;

import com.green.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepo extends JpaRepository<File, Long>, FileRepoCustom {
    @Query(value = "select * from file_attachment where file_name like concat('%',:name,'%') and status <> 2", nativeQuery = true)
    List<File> findAllByName(String name);

    @Query(value = "select * from file_attachment where id = :id and status <> 2", nativeQuery = true)
    Optional<File> findById(Long id);

    @Query(value = "select * from file_attachment where is_temp = true and status <> 2", nativeQuery = true)
    List<File> findAllByIsTempIsTrue();

    @Query(value = "select * from file_attachment where id in :ids and status <> 2", nativeQuery = true)
    List<File> findByIdIn(List<Long> ids);

}
