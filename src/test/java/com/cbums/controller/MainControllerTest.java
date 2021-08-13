package com.cbums.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /*
    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(MainController.class) // 테스트 대상 Controller 를 넣어준다.
                .alwaysExpect(MockMvcResultMatchers.status().isOk()) // 특정 필수 조건을 지정
                .build();
    }*/

    @Test
    @DisplayName("디폴트 페이지 GET")
    public void getDefaultPage() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("/default"))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 페이지 GET")
    public void getLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string("/login"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    @DisplayName("로그아웃 페이지 Post")
    public void postLogoutPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/logout")
                        .sessionAttr("login-user", 10))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/logout"))
                .andDo(print());
    }

    @Test
    @DisplayName("접근불가 페이지 GET")
    public void getDeniedPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/denied"))
                .andExpect(status().isOk())
                .andExpect(content().string("/denied"))
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 성공 페이지 GET")
    public void getLoginSuccessPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login-success"))
                .andExpect(status().isOk())
                .andExpect(content().string("/login-success"))
                .andDo(print());
    }

    @Test
    @DisplayName("지원서 페이지 GET")
    public void getFormPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/form-page"))
                .andExpect(status().isOk())
                .andExpect(content().string("/form"))
                .andDo(print());
    }

}