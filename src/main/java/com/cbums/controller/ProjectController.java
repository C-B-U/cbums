package com.cbums.controller;

import com.cbums.model.Member;
import com.cbums.model.Project;
import com.cbums.service.ProjectService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/")
    public JsonObject getProjectList() {
        JsonObject jsonObject = new JsonObject();
        List<Project> projectList = projectService.findProjects();
        JsonArray jsonArr = new Gson().toJsonTree(projectList).getAsJsonArray();
        jsonObject.add("projectList", jsonArr);

        return jsonObject;
    }

    @GetMapping("/{seq}")
    public JsonObject getProject(@PathVariable("seq") Long seq) {
        JsonObject jsonObject = new JsonObject();
        Project project = projectService.findProjectById(seq);
        jsonObject.addProperty("project",new Gson().toJson(project));
        return jsonObject;
    }



/*
    @GetMapping("/li")
    public List<Project> projectListViewPage() {
        List<Project> sample = new ArrayList<Project>();

        return sample;
    }
*/

}
