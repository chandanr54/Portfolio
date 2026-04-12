package com.project.portfolio.controller;

import com.project.portfolio.commanutil.MyMessages;
import com.project.portfolio.commanutil.PageName;
import com.project.portfolio.commanutil.Role;
import com.project.portfolio.dto.LoginRequest;
import com.project.portfolio.dto.LoginResponce;
import com.project.portfolio.dto.UsersRequest;
import com.project.portfolio.model.Users;
import com.project.portfolio.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MyMessages.AUTH_URL)
@RequiredArgsConstructor
@Slf4j
public class AuthCorntroller {

    private final UsersService usersService;


    @GetMapping
    public String testUrl() {
        return Role.ADMIN.name();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponce> login(@RequestBody @Valid LoginRequest loginRequest){
            log.debug("loginRequest:{}",loginRequest);
        LoginResponce loginResponce=    usersService.login(loginRequest);

        return ResponseEntity.ok(loginResponce);




    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UsersRequest usersRequest) {

        Users users=   usersService.createUser(usersRequest);
        return ResponseEntity.ok(users);

    }


}
