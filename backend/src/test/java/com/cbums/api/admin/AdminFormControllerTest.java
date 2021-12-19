package com.cbums.api.admin;

import com.cbums.config.auth.CustomOAuth2UserService;
import com.cbums.core.form.dto.FormRequest;
import com.cbums.core.form.dto.FormResponse;
import com.cbums.core.form.dto.QuestionRequest;
import com.cbums.core.form.service.FormService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureRestDocs
@WebMvcTest(controllers = AdminFormController.class)
@ExtendWith(RestDocumentationExtension.class)
class AdminFormControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FormService formService;

    @MockBean
    CustomOAuth2UserService customOAuth2UserService;

    private List<FormResponse> formResponses;
    private List<QuestionRequest> questionRequests;
    private FormResponse formResponse;
    private FormRequest formRequest;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentationContextProvider))
                //  .apply(springSecurity())
                .build();

        formResponses = new ArrayList<>();
        formResponses.add(new FormResponse("test1","intro", LocalDateTime.now(),LocalDateTime.now(),2,"test.jap"));
        formResponses.add(new FormResponse("test2","intro", LocalDateTime.now(),LocalDateTime.now(),2,"test.jap"));
        formResponses.add(new FormResponse("test3","intro", LocalDateTime.now(),LocalDateTime.now(),2,"test.jap"));
        formResponses.add(new FormResponse("test4","intro", LocalDateTime.now(),LocalDateTime.now(),2,"test.jap"));

        questionRequests = new ArrayList<>();
        for(int i=0;i<10;i++) {
            questionRequests.add(new QuestionRequest("question"+i));
        }

        formRequest = FormRequest.builder()
                .closeDateTime(LocalDateTime.now())
                .introduce("introduce")
                .openDateTime(LocalDateTime.now())
                .posterImage("posterImage")
                .title("title")
                .registerNumber(1)
                .questionRequests(questionRequests)
                .build();

        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @DisplayName("formList 조회")
    @Test
    public void getFormList() throws Exception {
        given(formService.findAll()).willReturn(formResponses);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/form")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("form 생성")
    @Test
    public void createForm() throws Exception {
        mockMvc.perform(post("/admin/form/")
                        .content(objectMapper.writeValueAsString(formRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("form 조회")
    @Test
    public void getForm() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/form/{id}",4l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("form 수정")
    @Test
    public void updateForm() throws Exception {
        mockMvc.perform(patch("/admin/form/{id}",10l)
                        .content(objectMapper.writeValueAsString(formRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("form 삭제")
    @Test
    public void deleteForm() throws Exception {
        mockMvc.perform(delete("/admin/form/{id}",10l))
                .andExpect(status().isCreated())
                .andDo(print());

    }

}