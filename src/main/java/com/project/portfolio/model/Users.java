package com.project.portfolio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.portfolio.commanutil.MyMessages;
import com.project.portfolio.commanutil.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = MyMessages.NAME_REQUIRED)
    private String name;
    private String lastName;
    @Column(unique = true, nullable = false)
    @Email(message = MyMessages.INVALID_EMAIL)
    @NotBlank(message = MyMessages.EMAIL_REQUIRED)
    private String email;
    private String password;

    @Column(unique = true, nullable = false)
    @NotBlank(message = MyMessages.USER_NAME_REQUIRED)
    private String userName;

    private String phone;
    private String address;
    private String  linkdinAddress;
    private String  gitHubLink;
    private String  website;

    @Enumerated(EnumType.STRING)
    private Role role= Role.USER;

    private Boolean isDelete=false;
    private Boolean  isActive=true;

        @OneToMany(mappedBy = "users",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY)
       @JsonIgnore
        private List<UserCompany> userCompanies=new ArrayList<>();

        @OneToMany(mappedBy = "users",
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                fetch = FetchType.LAZY)
        @JsonIgnore
        private List<UserProjects> userProjects=new ArrayList<>();

        @OneToMany(mappedBy = "users",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
        @JsonIgnore
        private List<Skills>   skills=new ArrayList<>();

    @CreationTimestamp
    private Date  createdDate;
    @UpdateTimestamp
    private Date lastModifiedDate;



}
