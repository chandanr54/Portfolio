package com.project.portfolio.dto;

import com.project.portfolio.commanutil.MyMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponce {


    private String token;

    private UsersResponce usersResponce;
}
