package com.project.portfolio.service;

import com.project.portfolio.dto.UserCompanyRequest;
import com.project.portfolio.dto.UserCompanyResponce;
import com.project.portfolio.model.UserCompany;

import java.util.List;

public interface UserCompanyService {
    UserCompany createUserCompany(UserCompanyRequest userCompanyRequest);

    List<UserCompanyResponce> getUserCompanyByUserId(String userid);

    UserCompanyResponce getUserCompanyById(String id);

    UserCompanyResponce updateUserCompany(UserCompanyRequest userCompanyRequest, String companyId);

    String deleteUserCompany(String id);
}
