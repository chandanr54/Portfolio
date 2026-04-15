package com.project.portfolio.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillResponce {
    private String id;
    private String skillName;
    private Long level;
    private Long duration;
    private String userId;
}
