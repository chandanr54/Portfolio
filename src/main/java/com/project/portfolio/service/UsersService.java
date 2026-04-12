package com.project.portfolio.service;

import com.project.portfolio.dto.LoginRequest;
import com.project.portfolio.dto.LoginResponce;
import com.project.portfolio.dto.UsersRequest;
import com.project.portfolio.dto.UsersResponce;
import com.project.portfolio.model.Users;

import java.util.List;

public interface UsersService {
    Users createUser(UsersRequest usersRequest);

    UsersResponce getUserByUserName(String userName);

    UsersResponce getUserById(String id);

    List<UsersResponce> getAllUsers();

    UsersResponce updateUser(UsersRequest usersRequest, String id);

    String deleteUserById(String id);

    LoginResponce login(LoginRequest loginRequest);
}
