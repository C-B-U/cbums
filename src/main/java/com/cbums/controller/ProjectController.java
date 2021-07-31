package com.cbums.controller;

import com.cbums.model.Member;
import com.cbums.model.Project;
import com.cbums.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project/**")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/")
    public String projectPage() {

        return "/project";
    }

    @GetMapping("/li")
    public List<Project> projectListViewPage() {
        List<Project> sample = new ArrayList<Project>();

        return sample;
    }


}
