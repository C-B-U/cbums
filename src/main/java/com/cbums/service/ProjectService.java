package com.cbums.service;

import com.cbums.model.Project;
import com.cbums.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> findProjects(){
        return projectRepository.findAll();
    }
}
