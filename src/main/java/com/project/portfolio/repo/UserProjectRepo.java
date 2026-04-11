package com.project.portfolio.repo;

import com.project.portfolio.model.UserProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjectRepo extends JpaRepository<UserProjects,String> {
    UserProjects findByIdAndIsDeleted(String id, boolean deleted);

    List<UserProjects> findByUserCompany_CompanyIdAndIsDeleted(String companyId, boolean isDeleted);

   List< UserProjects >findByUsersIdAndIsDeleted(String userId, boolean isDeleted);
}
