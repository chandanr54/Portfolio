package com.project.portfolio.repo;

import com.project.portfolio.model.UserCompany;
import com.project.portfolio.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCompanyRepo extends JpaRepository<UserCompany,String> {


    List<UserCompany> findByUsersIdAndIsDeletedAndIsActive(String usersId, Boolean isDeleted, Boolean isActive);

    UserCompany findByCompanyIdAndIsDeleted(String companyId, Boolean isDeleted);
}
