package com.project.portfolio.dto;


import com.project.portfolio.model.Users;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCompanyResponce {
    private String companyId;
    private String userId;
    private String companyName;
    private String companyAddress;
    private String joinDate;
    private String exitDate;
    private Long experience;
    //private Users users;
}
