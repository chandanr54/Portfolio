package com.project.portfolio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UserProjects {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String projectName;

    @Column(length = 500)
    private String projectDescription;
    private String projectUrl;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> projectsTechnology;
    private String gitUrl;

    //TODO: Adding project image filed

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_projects_user"))
    @JsonIgnore
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_projects_usercompany"))
    @JsonIgnore
    private UserCompany userCompany;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

    private boolean isDeleted = false;
    private boolean isActive = true;
}
