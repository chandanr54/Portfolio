package com.project.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String skillName;
    private Long level;
    private Long duration;

    private boolean isActive=true;
    private boolean isDeleted=false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_skills_user"))
    @JsonIgnore
    private Users users;

    @CreationTimestamp
    private Date creationDate;
    @UpdateTimestamp
    private Date lastModified;

}
