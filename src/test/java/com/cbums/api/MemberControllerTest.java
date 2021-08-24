package com.cbums.api;

import com.cbums.config.auth.CustomOAuth2UserService;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.core.member.dto.MemberAddDetailRequest;
import com.cbums.core.member.dto.MemberResponse;
import com.cbums.core.member.dto.UpdateIntroduceRequest;
import com.cbums.core.member.dto.UpdateNickNameRequest;
import com.cbums.core.member.service.MemberService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(controllers = MemberController.class)
@ExtendWith(RestDocumentationExtension.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @MockBean
    CustomOAuth2UserService customOAuth2UserService;

    private MemberAddDetailRequest addDetailRequest;
    private MemberResponse memberResponse;
    private UpdateNickNameRequest updateNickNameRequest;
    private UpdateIntroduceRequest updateIntroduceRequest;

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

        memberResponse = MemberResponse.builder()
                .memberId(1000L)
                .department("department")
                .introduce("introduce")
                .name("name")
                .nickName("nickName")
                .picture("picture")
                .registerNumber(1)
                .build();

        addDetailRequest = MemberAddDetailRequest.builder()
                .name("이름")
                .phoneNumber("010-2222-3333")
                .department("it경영")
                .build();

        updateNickNameRequest = UpdateNickNameRequest.builder()
                .nickName("닉네임")
                .build();

        updateIntroduceRequest = UpdateIntroduceRequest.builder()
                .introduce("자기소개입니당")
                .build();

        objectMapper = new ObjectMapper();
    }

    @DisplayName("상세정보 입력")
    @Test
    public void addDetail() throws Exception {
        mockMvc.perform(post("/member/detail")
                        .content(new ObjectMapper().writeValueAsBytes(addDetailRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("닉네임 변경")
    @Test
    public void updateNickName() throws Exception {
        mockMvc.perform(patch("/member/nick-name/")
                .content(objectMapper.writeValueAsString(updateNickNameRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("자기소개 변경")
    @Test
    public void updateIntroduce() throws Exception {
        mockMvc.perform(patch("/member/introduce/")
                        .content(objectMapper.writeValueAsString(updateIntroduceRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("member 조회")
    @Test
    public void getMember() throws Exception {
        //given
        given(memberService.findMember(any())).willReturn(memberResponse);

        //when
        mockMvc.perform(RestDocumentationRequestBuilders.get("/member/{id}", 1000l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
        //.andDo(MemberDocumentation.getMember());
    }


}