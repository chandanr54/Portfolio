package com.project.portfolio.repo;

import com.project.portfolio.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, String> {
    Users findUsersByUserNameAndIsDelete(String userName, Boolean isDelete);

    Users findByIdAndIsDelete(String id, Boolean isDelete);

    List<Users> findByIsDelete(Boolean isDelete);
}
