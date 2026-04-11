package com.project.portfolio.service.impl;

import com.project.portfolio.commanutil.Role;
import com.project.portfolio.dto.UserCompanyRequest;
import com.project.portfolio.dto.UserCompanyResponce;
import com.project.portfolio.dto.UsersRequest;
import com.project.portfolio.dto.UsersResponce;
import com.project.portfolio.exception.UserNotFoundException;
import com.project.portfolio.model.UserCompany;
import com.project.portfolio.model.Users;
import com.project.portfolio.repo.UserCompanyRepo;
import com.project.portfolio.service.UserCompanyService;
import com.project.portfolio.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCompanyServiceImpl implements UserCompanyService {

    private final UserCompanyRepo userCompanyRepo;
    private final UsersServiceImpl usersServiceImpl;

    @Override
    public UserCompany createUserCompany(UserCompanyRequest userCompanyRequest) {
        UserCompany userCompany = maptoUsers(userCompanyRequest);
        userCompanyRepo.save(userCompany);
        return  userCompanyRepo.save(userCompany);
    }

    @Override
    public List<UserCompanyResponce> getUserCompanyByUserId(String userid) {
        List<UserCompany> byUsersIdAndIsDeletedAndIsActive = userCompanyRepo.
                                            findByUsersIdAndIsDeletedAndIsActive(userid, false, true);
        return maptoUsersResponce(byUsersIdAndIsDeletedAndIsActive);
    }

    @Override
    public UserCompanyResponce getUserCompanyById(String id) {
        return maptoUsersResponce(findByUserCompanyId(id));
       // return null;
    }

    @Override
    public UserCompanyResponce updateUserCompany(UserCompanyRequest userCompanyRequest, String companyId) {
        //:TODO:Later it update
        return null;
    }

    @Override
    public String deleteUserCompany(String id) {

        UserCompany userCompanyId = findByUserCompanyId(id);
        userCompanyId.setIsDeleted(true);
        userCompanyId.setIsActive(false);
        return userCompanyRepo.save(userCompanyId) !=null?"User Comany is delete with id::"+id:"Something went wrong" ;



    }

    /**@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
    public UserCompany findByUserCompanyId(String id) {

        UserCompany byIdAndIsDelete = userCompanyRepo.findByCompanyIdAndIsDeleted(id, false);
        if (byIdAndIsDelete == null) {
            throw new UserNotFoundException("User Company not found with Company: " + id);
        }
        return byIdAndIsDelete;
    }


    private UserCompany maptoUsers(UserCompanyRequest userCompanyRequest) {
        Users users = usersServiceImpl.findByUserId(userCompanyRequest.getUserId());
        return UserCompany.builder()
                .companyName(userCompanyRequest.getCompanyName())
                .isActive(true)
                .isDeleted(false)
                .companyAddress(userCompanyRequest.getCompanyAddress())
                .exitDate(userCompanyRequest.getExitDate())
                .joinDate(userCompanyRequest.getJoinDate())
                .experience(userCompanyRequest.getExperience())
                .users(users)
                .build();
    }

    private UserCompanyResponce maptoUsersResponce(UserCompany usersCompany) {
        return UserCompanyResponce.builder()
                .companyId(usersCompany.getCompanyId())
                .companyName(usersCompany.getCompanyName())
                .exitDate(usersCompany.getExitDate())
                .joinDate(usersCompany.getJoinDate())
                .experience(usersCompany.getExperience())
                .userId(usersCompany.getUsers().getId())
                .companyAddress(usersCompany.getCompanyAddress())
                .build();
    }


    private List<UserCompanyResponce> maptoUsersResponce(List<UserCompany> usersCompany) {
        return usersCompany.stream()
                .map(this::maptoUsersResponce)
                .collect(Collectors.toList());
    }

}
