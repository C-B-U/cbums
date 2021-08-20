package com.cbums.controller;

import com.cbums.model.Project;
import com.cbums.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("")
    public  List<Project> getProjectList() {

        List<Project> projectList = projectService.findProjects();

        return projectList;
    }

    @GetMapping("/{seq}")
    public Project getProject(@PathVariable("seq") Long seq) {

        Project project = projectService.findProjectById(seq);

        return project;
    }

}
