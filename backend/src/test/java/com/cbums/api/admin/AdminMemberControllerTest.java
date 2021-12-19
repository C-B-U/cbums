package com.cbums.api.admin;

import com.cbums.config.auth.CustomOAuth2UserService;
import com.cbums.core.member.domain.AuthProvider;
import com.cbums.core.member.domain.UserRoleType;
import com.cbums.core.member.dto.MemberResponseForAdmin;
import com.cbums.core.member.dto.UpdateRoleTypeRequest;
import com.cbums.core.member.service.MemberService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(controllers = AdminMemberController.class)
@ExtendWith(RestDocumentationExtension.class)
class AdminMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @MockBean
    CustomOAuth2UserService customOAuth2UserService;

    private MemberResponseForAdmin memberResponseForAdmin;
    private List<MemberResponseForAdmin> memberResponseForAdmins;
    private ObjectMapper objectMapper;
    private UpdateRoleTypeRequest updateRoleTypeRequest;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentationContextProvider))
                //  .apply(springSecurity())
                .build();

        memberResponseForAdmin = MemberResponseForAdmin.builder()
                .email("email@emaoi.com")
                .name("이름")
                .picture("picture.jap")
                .provider(AuthProvider.KAKAO.name())
                .role(UserRoleType.MEMBER.getKey())
                .memberId(1l)
                .department("아경")
                .introduce("안연안녕")
                .nickName("별명별명")
                .phoneNumber("010-2892-1234")
                .registerNumber(1)
                .resign(false)
                .build();

        memberResponseForAdmins = new ArrayList<>();
        memberResponseForAdmins.add(memberResponseForAdmin);
        memberResponseForAdmins.add(memberResponseForAdmin);
        memberResponseForAdmins.add(memberResponseForAdmin);
        memberResponseForAdmins.add(memberResponseForAdmin);

        objectMapper = new ObjectMapper();

        updateRoleTypeRequest = new UpdateRoleTypeRequest("MEMBER");
    }

    @DisplayName("맴버 전체 조회")
    @Test
    public void getMemberList() throws Exception {
        given(memberService.findAllForAdmin()).willReturn(memberResponseForAdmins);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/member")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("맴버 조회")
    @Test
    public void getMember() throws Exception {
        given(memberService.findMemberForAdmin(any())).willReturn(memberResponseForAdmin);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/admin/member/{id}", 10l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("맴버 삭제")
    @Test
    public void deleteMember() throws Exception {
        mockMvc.perform(delete("/admin/member/{id}",10l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("맴버 권한 변경")
    @Test
    public void updateMEmberRoleType() throws Exception {
        mockMvc.perform(patch("/admin/member/role/{id}",33l)
                        .content(objectMapper.writeValueAsString(updateRoleTypeRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}