package com.cbums.api.admin;

import com.cbums.api.MemberController;
import com.cbums.config.auth.CustomOAuth2UserService;
import com.cbums.core.answer.dto.AnswerResponse;
import com.cbums.core.answer.service.AnswerService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(controllers = AdminAnswerController.class)
@ExtendWith(RestDocumentationExtension.class)
class AdminAnswerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AnswerService answerService;

    @MockBean
    CustomOAuth2UserService customOAuth2UserService;

    private List<AnswerResponse> answerResponses;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentationContextProvider))
                //  .apply(springSecurity())
                .build();

        answerResponses = new ArrayList<>();
        answerResponses.add(new AnswerResponse(1l,1l,1l,"ts1"));
        answerResponses.add(new AnswerResponse(2l,1l,2l,"ts2"));
        answerResponses.add(new AnswerResponse(3l,1l,3l,"ts3"));
        answerResponses.add(new AnswerResponse(4l,1l,4l,"ts4"));

    }

    @DisplayName("form id별로 제출된 지원서 조회")
    @Test
    public void getFormAnswerList() throws Exception {
        //given
        given(answerService.findAnswersByFormId(any())).willReturn(answerResponses);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/answer/{id}", 1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("form 제출한 member의 지원서 조회")
    @Test
    public void getFormAnswer() throws Exception {
        //given
        given(answerService.findFormAnswerByFormIdAndMemberId(any(), any())).willReturn(answerResponses);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/answer/{f-id}/member/{m-id}", 1l, 1000l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}