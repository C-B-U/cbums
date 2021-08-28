package com.cbums.api;

import com.cbums.config.auth.CustomOAuth2UserService;
import com.cbums.core.project.dto.ProjectRequest;
import com.cbums.core.project.service.ProjectService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(controllers = ProjectController.class)
@ExtendWith(RestDocumentationExtension.class)
class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProjectService projectService;

    @MockBean
    CustomOAuth2UserService customOAuth2UserService;

    private ProjectRequest projectRequest;

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

        projectRequest = ProjectRequest.builder()
                .name("name")
                .maximumMember(4)
                .producerHidden(true)
                .icon("dddd.ico")
                .build();

        objectMapper = new ObjectMapper();
    }

    @DisplayName("project 리스트 조회")
    @Test
    public void getProjects() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/project")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("project 조회")
    @Test
    public void getProject() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/project/{id}",10l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("project 생성")
    @Test
    public void createProject() throws Exception {
        mockMvc.perform(post("/project")
                        .content(objectMapper.writeValueAsString(projectRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("project 생성")
    @Test
    public void updateProject() throws Exception {
        mockMvc.perform(patch("/project/{id}",1l)
                        .content(objectMapper.writeValueAsString(projectRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @DisplayName("recruit update")
    @Test
    public void updateRecruit() throws Exception {
        mockMvc.perform(patch("/project/recruit/{id}",1l)
                        .content(objectMapper.writeValueAsString(projectRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

}