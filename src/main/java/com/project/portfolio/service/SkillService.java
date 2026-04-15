package com.project.portfolio.service;

import com.project.portfolio.dto.SkillRequest;
import com.project.portfolio.dto.SkillResponce;
import com.project.portfolio.model.Skills;

import java.util.List;

public interface SkillService {
    Skills createSkill(SkillRequest skillRequest);

    List<Skills> getSkillsById(String id);

    List<Skills> getSkillsByUserId(String userId);

    List<Skills> getSkillsByUserName(String username);

    SkillResponce activeOrDeactive(Boolean boolen);
}
