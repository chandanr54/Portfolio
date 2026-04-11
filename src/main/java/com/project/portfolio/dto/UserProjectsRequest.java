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
public class UserProjectsRequest {




    private String projectName;
    private String projectDescription;
    private String projectUrl;
    private List<String> projectsTechnology;
    private String gitUrl;
    private String userId;
    private String companyId;
  //  private Users user;
   // private UserCompany userCompany;



}
