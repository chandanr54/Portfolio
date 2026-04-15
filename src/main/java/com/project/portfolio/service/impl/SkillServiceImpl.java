package com.project.portfolio.service.impl;

import com.project.portfolio.dto.SkillRequest;
import com.project.portfolio.dto.SkillResponce;
import com.project.portfolio.dto.UsersResponce;
import com.project.portfolio.exception.UserNotFoundException;
import com.project.portfolio.model.Skills;
import com.project.portfolio.model.Users;
import com.project.portfolio.repo.SkillRepo;
import com.project.portfolio.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepo skillRepo;

    private final  UsersServiceImpl  usersServiceImpl;

    @Override
    public Skills createSkill(SkillRequest skillRequest) {

       Skills skills= mpatoSkills(skillRequest);
        return skillRepo.save(skills);
    }


    @Override
    public List<Skills> getSkillsById(String id) {

        List<Skills> skills= skillRepo.findByIdAndDeletedIs(id,false);
        if(skills==null && skills.isEmpty()){
            throw new UserNotFoundException("Skill Not Found with given ID:"+id);
        }
        return skills;
    }

    @Override
    public List<Skills> getSkillsByUserId(String userId) {
        List<Skills> skills= skillRepo.findByUsers_IdAndIsDeleted(userId,false);
        if(skills==null && skills.isEmpty()){
            throw new UserNotFoundException("Skill Not Found with given ID:"+userId);
        }
        return skills;
    }

    @Override
    public List<Skills> getSkillsByUserName(String username) {
        Users user = usersServiceImpl.findByUserNames(username);
        List<Skills> skillsByUserId = getSkillsByUserId(user.getId());
        return skillsByUserId;
    }

    @Override
    public SkillResponce activeOrDeactive(Boolean boolen) {
        return null;
    }


    /**@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/


    private Skills mpatoSkills(SkillRequest skillRequest) {

        return Skills.builder()
                .skillName(skillRequest.getSkillName())
                .level(skillRequest.getLevel())
                .users(getUserById(skillRequest.getUserId()))
                .duration(skillRequest.getDuration())
                .isActive(true)
                .isDeleted(false)
                .build();
    }

    private Users getUserById( String userId) {
        Users user = usersServiceImpl.findByUserId(userId);
        return user;

    }


}
