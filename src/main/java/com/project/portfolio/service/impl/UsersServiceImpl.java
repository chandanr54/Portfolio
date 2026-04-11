package com.project.portfolio.service.impl;

import com.project.portfolio.commanutil.Role;
import com.project.portfolio.dto.UsersRequest;
import com.project.portfolio.dto.UsersResponce;
import com.project.portfolio.exception.UserNotFoundException;
import com.project.portfolio.model.Users;
import com.project.portfolio.repo.UsersRepo;
import com.project.portfolio.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

        private final UsersRepo usersRepo;

        @Override
        public Users createUser(UsersRequest usersRequest) {


                Users users = maptoUsers(usersRequest);

                return usersRepo.save(users);
        }

    @Override
    public UsersResponce getUserByUserName(String userName) {
        Users usersByUserName = usersRepo.findUsersByUserNameAndIsDelete(userName,false);
        if (usersByUserName == null) {
            throw new UserNotFoundException("User not found with username: " + userName);
        }

        return maptoUsersResponce(usersByUserName);
    }

    @Override
    public UsersResponce getUserById(String id) {
        Users users = findByUserId(id);
        return maptoUsersResponce(users);
    }

    @Override
    public List<UsersResponce> getAllUsers() {
        List<Users> all = usersRepo.findByIsDelete(false);
        return maptoUsersResponce(all);
    }

    @Override
    public UsersResponce updateUser(UsersRequest usersRequest, String id) {
        Users users = findByUserId(id);


            if(usersRequest.getName()!=null && !usersRequest.getName().isEmpty()) {
                users.setName(usersRequest.getName());
            }
        if(usersRequest.getLastName()!=null && !usersRequest.getLastName().isEmpty()) {
            users.setLastName(usersRequest.getLastName());
        }
        if(usersRequest.getEmail()!=null && !usersRequest.getEmail().isEmpty()) {
            users.setEmail(usersRequest.getEmail());
        }
        if(usersRequest.getPassword()!=null && !usersRequest.getPassword().isEmpty()) {
            users.setPassword(usersRequest.getPassword());
        }
        if(usersRequest.getAddress()!=null && !usersRequest.getAddress().isEmpty()) {
            users.setAddress(usersRequest.getAddress());
        }
        if(usersRequest.getPhone()!=null && !usersRequest.getPhone().isEmpty()) {
            users.setPhone(usersRequest.getPhone());
        }
        if(usersRequest.getGitHubLink()!=null && !usersRequest.getGitHubLink().isEmpty()) {
            users.setGitHubLink(usersRequest.getGitHubLink());
        }
        if(usersRequest.getLinkdinAddress()!=null && !usersRequest.getLinkdinAddress().isEmpty()) {
            users.setLinkdinAddress(usersRequest.getLinkdinAddress());
        }
        if(usersRequest.getWebsite()!=null && !usersRequest.getWebsite().isEmpty()) {
            users.setWebsite(usersRequest.getWebsite());
        }



        Users save = usersRepo.save(users);
        return maptoUsersResponce(save);
    }

    @Override
    public String deleteUserById(String id) {
        Users users = findByUserId(id);

        users.setIsDelete(true);
        users.setIsActive(false);
       return usersRepo.save(users) !=null? "User is Delete id:"+id:"Something went wrong";

    }


    /**#####################################################################################*/
public Users findByUserId(String id) {

    Users byIdAndIsDelete = usersRepo.findByIdAndIsDelete(id, false);
    if (byIdAndIsDelete == null) {
        throw new UserNotFoundException("User not found with username: " + id);
    }
    return byIdAndIsDelete;
}


        private Users maptoUsers(UsersRequest usersRequest) {
                return Users.builder()
                        .userName(usersRequest.getUsername())
                        .name(usersRequest.getName())
                        .lastName(usersRequest.getLastName())
                        .email(usersRequest.getEmail())
                        .password(usersRequest.getPassword())
                        .phone(usersRequest.getPhone())
                        .address(usersRequest.getAddress())
                        .linkdinAddress(usersRequest.getLinkdinAddress())
                        .gitHubLink(usersRequest.getGitHubLink())
                        .website(usersRequest.getWebsite())
                        .role(Role.USER)
                        .isDelete(false)
                        .isActive(true)
                        .build();
        }

        private UsersResponce maptoUsersResponce(Users users) {
                return UsersResponce.builder()
                        .id(users.getId())
                        .username(users.getUserName())
                        .name(users.getName())
                        .lastName(users.getLastName())
                        .email(users.getEmail())
                        .phone(users.getPhone())
                        .address(users.getAddress())
                        .linkdinAddress(users.getLinkdinAddress())
                        .gitHubLink(users.getGitHubLink())
                        .website(users.getWebsite())
                        .role(users.getRole())
                        .build();
        }


        private List<UsersResponce> maptoUsersResponce(List<Users> users) {
                return users.stream()
                        .map(this::maptoUsersResponce)
                        .collect(Collectors.toList());
        }
}
