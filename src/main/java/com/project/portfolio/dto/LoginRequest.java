package com.project.portfolio.dto;

import com.project.portfolio.commanutil.MyMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @NotBlank(message = MyMessages.USER_NAME_REQUIRED)
    private String username;
    @NotBlank(message = MyMessages.PASSWORD_REQUIRED)
    private String password;
}
