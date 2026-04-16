package com.project.portfolio.controller;


import com.project.portfolio.commanutil.MyMessages;
import com.project.portfolio.dto.UserProjectsRequest;
import com.project.portfolio.model.UserProjects;
import com.project.portfolio.repo.UserProjectRepo;
import com.project.portfolio.service.UserProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MyMessages.PROJECT_URL)
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserProjectController {

    private final UserProjectService userProjectService;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody UserProjectsRequest userProjectsRequest) {
     UserProjects userProjects= userProjectService.createProject(userProjectsRequest);
     return ResponseEntity.ok(userProjects);

    }

    @GetMapping("/byprojectid")
    public ResponseEntity<?> getUserProjectsById(@RequestParam String id) {
        UserProjects userProjects= userProjectService.getUserProjectsById(id);
        return ResponseEntity.ok(userProjects);

    }

    @GetMapping("/byuserid")
    public ResponseEntity<?> getUserProjectsByUserId(@RequestParam String userId) {
        List<UserProjects> userProjects= userProjectService.getUserProjectsByUserId(userId);
        return ResponseEntity.ok(userProjects);

    }

    @GetMapping("/bycompanyid")
    public ResponseEntity<?> getUserProjectsByCompnayId(@RequestParam String companyId) {
        List<UserProjects> userProjects= userProjectService.getUserProjectsByCompnayId(companyId);
        return ResponseEntity.ok(userProjects);

    }



}
