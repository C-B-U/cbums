package com.cbums.service;

import com.cbums.model.Project;
import com.cbums.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> findProjects(){
        return projectRepository.findAll();
    }

    public Project findProjectById(Long id) {
        Optional<Project> byId = projectRepository.findById(id);
        return byId.get();
    }
}
