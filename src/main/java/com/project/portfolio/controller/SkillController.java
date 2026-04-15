package com.project.portfolio.controller;


import com.project.portfolio.commanutil.MyMessages;
import com.project.portfolio.dto.SkillRequest;
import com.project.portfolio.dto.SkillResponce;
import com.project.portfolio.model.Skills;
import com.project.portfolio.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MyMessages.SKILL_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class SkillController {

    private final SkillService skillService;

    @PostMapping
    public ResponseEntity<Skills> createSkill(@RequestBody SkillRequest skillRequest){
       Skills skills=  skillService.createSkill(skillRequest);

       return ResponseEntity.ok().body(skills);
    }

//    @PostMapping
//    public ResponseEntity<List<Skills>> createSkill(@RequestBody List<SkillRequest> skillRequest){
//        List<Skills> skills=  skillService.createSkill(skillRequest);
//
//        return ResponseEntity.ok().body(skills);
//    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<Skills>> getSkillsById(@PathVariable String  id){
        List<Skills> skills=   skillService.getSkillsById(id);
        return ResponseEntity.ok().body(skills);
    }
    @GetMapping("/userid/{userId}")
    public ResponseEntity<List<Skills>> getSkillsByUserId(@PathVariable String  userId){
        List<Skills> skills=   skillService.getSkillsByUserId(userId);
        return ResponseEntity.ok().body(skills);
    }


    @GetMapping("/username/{username}")
    public ResponseEntity<List<Skills>> getSkillsByUserName(@PathVariable String  username){
        List<Skills> skills=   skillService.getSkillsByUserName(username);
        return ResponseEntity.ok().body(skills);
    }

    @PostMapping("/active/{boolen}")
    public SkillResponce  activeOrDeactive(@PathVariable Boolean boolen){
     SkillResponce skillResponce= skillService.activeOrDeactive(boolen);
     return skillResponce;
    }
}
