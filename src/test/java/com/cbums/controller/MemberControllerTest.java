package com.cbums.controller;

import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext ctx;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 한글 깨짐 처리
                .build();
    }

    @Test
    @DisplayName("회원가입 승인여부 확인 페이지_GET")
    public void getSignUpPage() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/member/sign-up"))
                .andExpect(status().isOk())
                .andExpect(content().string("/member/sign-up"))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 진행 form 페이지_GET")
    public void getSignUpFormPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/member/sign-up-form"))
                .andExpect(status().isOk())
                .andExpect(content().string("/member/sign-up-form"))
                .andDo(print());
    }

}