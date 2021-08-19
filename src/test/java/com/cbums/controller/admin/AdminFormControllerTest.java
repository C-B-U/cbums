package com.cbums.controller.admin;

import com.cbums.controller.postParameter.CreateFormFormParameter;
import com.cbums.core.form.service.FormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class AdminFormControllerTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext ctx;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    FormService formService;

    @Autowired
    HttpServletRequest request;

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
                        .contentType(MediaType.APPLICATION_JSON)
                        .sessionAttr("login-user", 30l))
                .andExpect(status().is3xxRedirection())
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

    @Test
    @DisplayName("지원서 수정_PATCH")
    public void patchForm() throws Exception {
        CreateFormFormParameter createFormFormParameter =
                new CreateFormFormParameter();
        createFormFormParameter.setTitle("제목입니당");
        createFormFormParameter.setIntroduce("지원서에 대한 소개입니당");
        createFormFormParameter.setRegisterNumber(3);
        createFormFormParameter.setOpenDateTime(LocalDateTime.now());
        createFormFormParameter.setCloseDateTime(LocalDateTime.now());
        logger.info("cffp closeDateTime 생성");

        String content = objectMapper.writeValueAsString(createFormFormParameter);
        logger.info(content);

        mockMvc.perform(MockMvcRequestBuilders.patch("/admin/form/8")
                        .sessionAttr("login-user", 30l)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/form/" + 8))
                .andDo(print());
    }

    @Test
    @DisplayName("form 삭제")
    public void deleteForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/admin/form/29")
                        .sessionAttr("login-user", 30l))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/form"))
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