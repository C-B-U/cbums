package com.cbums.api;

import com.cbums.common.security.EncryptionService;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.core.member.dto.MemberResponse;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(controllers = MemberController.class)
//@ExtendWith(MockitoExtension.class)
@ExtendWith(RestDocumentationExtension.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @MockBean
    EncryptionService encryptionService;

    private Member member;
    private MemberResponse memberResponse;
    private SignUpRequest signUpRequest;
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

        member = new Member();
       // member.setMemberId(1000l);
        member.setEmail("test@test.com");
        member.setPassword(encryptionService.encode("0000"));
        member.setName("테스트");
        member.setPhoneNumber("010-0000-0000");
        member.setClassNumber("2018111111");
        member.setDepartment("컴공소공아경산경");

        signUpRequest = SignUpRequest.builder()
                .email("test@test.com")
                .name("테스트이름")
                .phoneNumber("010-0000-0000")
                .classNumber("2018314014")
                .department("IT경영학과")
                .build();

        memberResponse = MemberResponse.builder()
                .email("test@test.com")
                .name("테테테")
                .nickName("별명임")
                .registerNumber(1)
                .department("경경경")
                .profileImage("dkdkdk.jpg")
                .introduce("하이하이")
                .build();

        objectMapper = new ObjectMapper();
    }

    @DisplayName("member 등록")
    @Test
    public void registerMember() throws Exception {
        given(memberService.registerMember(any())).willReturn(1L);


        mockMvc.perform(post("/member")
                .content(objectMapper.writeValueAsString(signUpRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/content/1"))
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