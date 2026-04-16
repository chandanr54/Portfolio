package com.project.portfolio.dto;



import com.project.portfolio.model.UserCompany;
import com.project.portfolio.model.Users;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProjectsResponce {


    private String id;
    private String userId;
    private String companyId;

    private String projectName;


    private String projectDescription;
    private String projectUrl;

    private List<String> projectsTechnology;
    private String gitUrl;
    //TODO: Adding project image filed


//    private Users user;
//
//
//    private UserCompany userCompany;


    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
