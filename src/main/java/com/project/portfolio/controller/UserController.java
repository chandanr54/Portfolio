package com.project.portfolio.controller;


import com.project.portfolio.commanutil.MyMessages;
import com.project.portfolio.commanutil.Role;
import com.project.portfolio.dto.LoginRequest;
import com.project.portfolio.dto.LoginResponce;
import com.project.portfolio.dto.UsersRequest;
import com.project.portfolio.dto.UsersResponce;
import com.project.portfolio.model.Users;
import com.project.portfolio.security.JwtUtils;
import com.project.portfolio.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MyMessages.USER_URL)
@RequiredArgsConstructor
public class UserController {

            private final UsersService  usersService;

            private String adminRole=Role.ADMIN.name();
            private String userRole=Role.USER.name();

            @PostMapping
            public ResponseEntity<Users> createUser(@RequestBody UsersRequest  usersRequest) {

                Users users=   usersService.createUser(usersRequest);
             return ResponseEntity.ok(users);

            }

            @GetMapping("/{username}")
            public ResponseEntity<UsersResponce> getUserByUserName(@PathVariable("username") String username) {
                UsersResponce users=  usersService.getUserByUserName(username);
                return ResponseEntity.ok(users);
            }

            @GetMapping("/id/{id}")
            public ResponseEntity<UsersResponce> getUserById(@PathVariable("id") String id) {
                UsersResponce users=  usersService.getUserById(id);
                return ResponseEntity.ok(users);
            }

            @GetMapping("/alluser")
            public ResponseEntity<List<UsersResponce>> getAllUsers() {
              List<UsersResponce> users= usersService.getAllUsers();
              return ResponseEntity.ok(users);
            }

            @PutMapping()
            public ResponseEntity<UsersResponce> updateUser(@RequestBody UsersRequest  usersRequest,@RequestParam String id  ) {

                System.out.println("Strat...");
                System.out.println(usersRequest.getEmail());
                UsersResponce users=  usersService.updateUser(usersRequest,id);
                return ResponseEntity.ok(users);
            }


            @PreAuthorize("hasRole('ADMIN')")
            @DeleteMapping("/{id}")
            public String deleteUserById(@PathVariable("id") String id) {

                return   usersService.deleteUserById(id);
            }






}
