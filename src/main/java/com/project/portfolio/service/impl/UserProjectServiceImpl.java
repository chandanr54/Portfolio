package com.project.portfolio.service.impl;

import com.project.portfolio.dto.UserProjectsRequest;
import com.project.portfolio.dto.UserProjectsResponce;
import com.project.portfolio.exception.UserNotFoundException;
import com.project.portfolio.model.UserCompany;
import com.project.portfolio.model.UserProjects;
import com.project.portfolio.model.Users;

import com.project.portfolio.repo.UserProjectRepo;
import com.project.portfolio.service.UserProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserProjectServiceImpl implements UserProjectService {

    private final UserProjectRepo  userProjectRepo;

    private final UsersServiceImpl  usersServiceImpl;
    private final UserCompanyServiceImpl userCompanyServiceImpl;



    @Override
    public UserProjects createProject(UserProjectsRequest userProjectsRequest) {
        UserProjects userProjects = maptoUserProjects(userProjectsRequest);

        return userProjectRepo.save(userProjects);
    }

    @Override
    public UserProjects getUserProjectsById(String id) {
        return findUserProjectsById(id);
      // return null;
    }

    @Override
    public List<UserProjects> getUserProjectsByUserId(String userId) {

        return findUserProjectsByUserId(userId);
    }

    @Override
    public List<UserProjects> getUserProjectsByCompnayId(String companyId) {
        return findUserProjectsByCompanyId(companyId);
    }



    /**@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/

    public List<UserProjects> findUserProjectsByUserId(String userId) {
        List<UserProjects> byUserIdAndIsDeleted = userProjectRepo.findByUsersIdAndIsDeleted(userId, false);
        if(byUserIdAndIsDeleted == null && byUserIdAndIsDeleted.isEmpty()){
            throw new UserNotFoundException("UserProjects not found By User Id::"+userId);
        }

        return byUserIdAndIsDeleted;
    }

    public List<UserProjects> findUserProjectsByCompanyId(String companyId) {

        List<UserProjects> byUserCompanyIdAndIsDeleted = userProjectRepo.findByUserCompany_CompanyIdAndIsDeleted(companyId, false);
        if(byUserCompanyIdAndIsDeleted == null && byUserCompanyIdAndIsDeleted.isEmpty()){
            throw new UserNotFoundException("UserProjects not found By Company Id::"+companyId);
        }
        return byUserCompanyIdAndIsDeleted;
    }

    public UserProjects findUserProjectsById(String id) {
        UserProjects byIdAndIsDelete = userProjectRepo.findByIdAndIsDeleted(id, false);
        if(byIdAndIsDelete == null){
            throw new UserNotFoundException("Project not found with id: " + id  );
        }
        return byIdAndIsDelete;
    }

    private UserProjects maptoUserProjects(UserProjectsRequest userProjectsRequest) {

        Users users = usersServiceImpl.findByUserId(userProjectsRequest.getUserId());
        UserCompany userCompany = userCompanyServiceImpl.findByUserCompanyId(userProjectsRequest.getCompanyId());


        return UserProjects.builder()
                .users(users)
                .userCompany(userCompany)
                .projectName(userProjectsRequest.getProjectName())
                .projectDescription(userProjectsRequest.getProjectDescription())
                .projectUrl(userProjectsRequest.getProjectUrl())
                .projectsTechnology(userProjectsRequest.getProjectsTechnology())
                .isDeleted(false)
                .isActive(true)
                .gitUrl(userProjectsRequest.getGitUrl())
                .build();
    }


    private UserProjectsResponce maptoUserProjectsResponce(UserProjects userProjects) {
        return UserProjectsResponce.builder()
                .id(userProjects.getId())
                .projectsTechnology(userProjects.getProjectsTechnology())
                .companyId(userProjects.getUserCompany().getCompanyId())
                .projectName(userProjects.getProjectName())
                .projectDescription(userProjects.getProjectDescription())
                .projectUrl(userProjects.getProjectUrl())
                .userId(userProjects.getUsers().getId())
                .gitUrl(userProjects.getGitUrl())
                .build();

    }
    private List<UserProjectsResponce> maptoUserProjectsResponce(List<UserProjects> userProjects) {
        return userProjects.stream().
                map(this::maptoUserProjectsResponce).
                collect(Collectors.toList());
    }

}
