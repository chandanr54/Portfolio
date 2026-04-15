package com.project.portfolio.repo;

import com.project.portfolio.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SkillRepo extends JpaRepository<Skills,String> {
    List<Skills> findByIdAndDeletedIs(String id, boolean deleted);

    List<Skills> findByUsers_IdAndIsDeleted(String userId, boolean deleted);
}
