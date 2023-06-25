package com.springbootstarter.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AuthenticationRequestDto {

    private String username;
    String password;

    public AuthenticationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
