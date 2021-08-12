package com.cbums.controller.admin;

import com.cbums.controller.postParameter.CreateFormFormParameter;
import com.cbums.service.FormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class AdminFormControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext ctx;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    FormService formService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 한글 깨짐 처리
                .alwaysExpect(status().isOk())
                .alwaysExpect(content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("관리자 지원서 리스트_GET")
    public void getFormList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/form"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    @DisplayName("관리자 지원서 생성_ POST")
    public void postForm() throws Exception {

        // post로 전송되는 데이터 생성
        CreateFormFormParameter createFormFormParameter =
                new CreateFormFormParameter();
        createFormFormParameter.setTitle("제목입니당");
        createFormFormParameter.setIntroduce("지원서에 대한 소개입니당");
        createFormFormParameter.setRegisterNumber(3);
        createFormFormParameter.setOpenDateTime(LocalDateTime.now());
        createFormFormParameter.setCloseDateTime(LocalDateTime.now());

        String content = objectMapper.writeValueAsString(createFormFormParameter);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/form/")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("한 지원서 상세 설명 보기")
    public void getForm() throws Exception {
        //30번 데이터 미리 넣기
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/form/30"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    //update Form TODO
    @Test
    @DisplayName("지원서 수정_PATCH")
    public void patchForm() throws Exception {
        // post로 전송되는 데이터 생성
        CreateFormFormParameter createFormFormParameter =
                new CreateFormFormParameter();
        createFormFormParameter.setTitle("제목입니당");
        createFormFormParameter.setIntroduce("지원서에 대한 소개입니당");
        createFormFormParameter.setRegisterNumber(3);
        createFormFormParameter.setOpenDateTime(LocalDateTime.now());
        createFormFormParameter.setCloseDateTime(LocalDateTime.now());

        String content = objectMapper.writeValueAsString(createFormFormParameter);

        mockMvc.perform(MockMvcRequestBuilders.patch("/admin/form/8")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("질문 리스트_GET")
    public void getFormQuestionList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/form/question"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    @DisplayName("한 질문 보기")
    public void getFormQuestion() throws Exception {
        //30번 데이터 미리 넣기
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/form/question"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }



}