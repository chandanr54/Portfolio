package com.project.portfolio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.portfolio.commanutil.MyMessages;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String companyId;

    @NotBlank(message = MyMessages.COMPANY_NAME)
    private String companyName;
    private String companyAddress;
    private String joinDate;
    private String exitDate;
    private Long experience;
    private Boolean isActive=true;
    private Boolean isDeleted=false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_Company_user"))
    private Users users;


    @OneToMany(mappedBy = "userCompany",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private List<UserProjects> userProjects=new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

}
