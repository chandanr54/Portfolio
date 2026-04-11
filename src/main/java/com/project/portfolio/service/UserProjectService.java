package com.project.portfolio.service;

import com.project.portfolio.dto.UserProjectsRequest;
import com.project.portfolio.model.UserProjects;

import java.util.List;

public interface UserProjectService {
    UserProjects createProject(UserProjectsRequest userProjectsRequest);

    UserProjects getUserProjectsById(String id);

    List<UserProjects> getUserProjectsByUserId(String userId);

    List<UserProjects> getUserProjectsByCompnayId(String companyId);
}
