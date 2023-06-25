package com.springbootstarter.dto;

import com.springbootstarter.jwtAuthentication.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Role role;
}

