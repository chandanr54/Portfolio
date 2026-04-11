package com.project.portfolio.dto;

import com.project.portfolio.commanutil.Role;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersResponce {
    private String id;
    private String  username;
    private String name;
    private String lastName;

    private String email;
   // private String password;

    private String phone;
    private String address;
    private String  linkdinAddress;
    private String  gitHubLink;
    private String  website;
    private Role role;

}
