package com.cbums.service;

import com.cbums.model.Project;
import com.cbums.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> findProjects(){
        List<Project> projectList = projectRepository.findAll();
        if(projectList.isEmpty()) throw new NoSuchElementException();
        return projectList;
    }

    public Project findProjectById(Long id) {
        return  projectRepository.findById(id).orElseThrow(NullPointerException::new);
    }
}
