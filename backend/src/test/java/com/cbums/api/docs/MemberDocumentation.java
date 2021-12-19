package com.cbums.api.docs;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;

public class MemberDocumentation {
    public static RestDocumentationResultHandler getMember() {
        return document("UserCocktails/create",
                requestFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("이메일"),
                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("닉네임"),
                        fieldWithPath("registerNumber").type(JsonFieldType.NUMBER).description("기수"),
                        fieldWithPath("department").type(JsonFieldType.STRING).description("학과"),
                        fieldWithPath("profileImage").type(JsonFieldType.STRING).description("프로필 사진"),
                        fieldWithPath("introduce").type(JsonFieldType.STRING).description("자기소개")

                ),
                responseHeaders(
                        headerWithName("id").description("생성된 member id")
                ));
    }
}
