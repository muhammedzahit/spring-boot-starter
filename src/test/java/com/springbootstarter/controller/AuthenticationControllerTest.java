package com.springbootstarter.controller;

import com.springbootstarter.dto.AuthenticationRequestDto;
import com.springbootstarter.dto.AuthenticationResponseDto;
import com.springbootstarter.dto.RegisterRequestDto;
import com.springbootstarter.entity.User;
import com.springbootstarter.jwtAuthentication.AuthenticationService;
import com.springbootstarter.jwtAuthentication.Role;
import com.springbootstarter.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void a(){
        assertThat(1).isEqualTo(1);
    }

    @Test
    void testUserCanAccessAdminController() throws Exception {
        AuthenticationService authenticationService =  (AuthenticationService) applicationContext.getBean("authenticationService");
        authenticationService.register(new RegisterRequestDto(
                "mahammed",
                "zahid",
                "m@gmail.com",
                "1234",
                Role.USER
        ));

        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");
        for(User u : userRepository.findAll()) System.out.println(u.getFirstname());

        AuthenticationResponseDto authenticate = authenticationService.authenticate(new AuthenticationRequestDto(
                "m@gmail.com", "1234"
        ));

        MvcResult mvcResult = mockMvc.perform(
                get("/api/admin/hello").header(
                        HttpHeaders.AUTHORIZATION, "Bearer " + authenticate.getAccessToken()
                )
        ).andExpect(status().isForbidden()).andReturn();
    }
}