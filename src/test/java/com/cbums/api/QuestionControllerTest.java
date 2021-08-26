package com.cbums.api;

import com.cbums.config.auth.CustomOAuth2UserService;
import com.cbums.core.form.domain.Form;
import com.cbums.core.form.dto.QuestionResponse;
import com.cbums.core.form.service.FormService;
import com.cbums.core.member.service.MemberService;
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
@WebMvcTest(controllers = QuestionController.class)
@ExtendWith(RestDocumentationExtension.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    FormService formService;

    @MockBean
    CustomOAuth2UserService customOAuth2UserService;

    private List<QuestionResponse> questionResponses;
    Form form;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentationContextProvider))
                //  .apply(springSecurity())
                .build();
        form = Form.builder()
                .build();

        questionResponses = new ArrayList<>();
        questionResponses.add(new QuestionResponse(1l,"ct1", form));
        questionResponses.add(new QuestionResponse(2l,"ct2", form));
        questionResponses.add(new QuestionResponse(3l,"ct3", form));
        questionResponses.add(new QuestionResponse(4l,"ct4", form));

    }

    @DisplayName("question list 조회")
    @Test
    public void getQuestionList() throws Exception {
        //given
        given(formService.findQuestionsByFormId(any())).willReturn(questionResponses);
        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/question/{id}", 1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}