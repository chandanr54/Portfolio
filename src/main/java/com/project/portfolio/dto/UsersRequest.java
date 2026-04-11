package com.project.portfolio.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersRequest {

    private String  username;
    private String name;
    private String lastName;

    private String email;
    private String password;

    private String phone;
    private String address;
    private String  linkdinAddress;
    private String  gitHubLink;
    private String  website;



}
