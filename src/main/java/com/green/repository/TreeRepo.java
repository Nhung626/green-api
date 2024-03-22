package com.green.repository;

import com.green.model.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TreeRepo extends JpaRepository<Tree, Long>, TreeRepoCustom {
    @Query(value = "select * from tree where  id = :id and status <>2 ", nativeQuery = true)
    Optional<Tree> findById(Long id);
}
