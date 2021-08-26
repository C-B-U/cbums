package com.cbums.api;


import com.cbums.config.auth.CustomOAuth2UserService;
import com.cbums.core.answer.dto.AnswerRequest;
import com.cbums.core.answer.service.AnswerService;
import com.cbums.core.form.domain.Form;
import com.cbums.core.form.dto.FormRequest;
import com.cbums.core.form.dto.QuestionRequest;
import com.cbums.core.form.service.FormService;
import com.cbums.core.member.domain.AuthProvider;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.core.member.domain.UserRoleType;
import com.cbums.core.member.dto.MemberAddDetailRequest;
import com.cbums.core.member.dto.MemberResponse;
import com.cbums.core.member.dto.UpdateIntroduceRequest;
import com.cbums.core.member.dto.UpdateNickNameRequest;
import com.cbums.core.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(controllers = AnswerController.class)
@ExtendWith(RestDocumentationExtension.class)
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AnswerService answerService;
    @MockBean
    CustomOAuth2UserService customOAuth2UserService;

    private ObjectMapper objectMapper;
    private List<AnswerRequest> answerRequests;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentationContextProvider))
                //  .apply(springSecurity())
                .build();

        answerRequests = new ArrayList<>();
        answerRequests.add(new AnswerRequest(1l, "ans1"));
        answerRequests.add(new AnswerRequest(2l, "ans2"));
        answerRequests.add(new AnswerRequest(3l, "ans3"));
        answerRequests.add(new AnswerRequest(4l, "ans4"));
        objectMapper = new ObjectMapper();
    }

    @DisplayName("answer 추가")
    @Test
    public void postAnswer() throws Exception {
        mockMvc.perform(post("/answer/")
                .content(new ObjectMapper().writeValueAsBytes(answerRequests))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }


}