package com.green.repository;

import com.green.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiaryRepo extends JpaRepository<Diary, Long>, DiaryRepoCustom{
    @Query(value = "select * from diary where  id = :id and status <>2 ", nativeQuery = true)
    Optional<Diary> findById(Long id);
    @Query(value = "select * from diary where  tree_id = :treeId and status <>2 ", nativeQuery = true)
    List<Diary> findByTreeId(Long treeId);
}
