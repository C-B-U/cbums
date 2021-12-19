package com.cbums.core.board.domain;

import com.cbums.core.project.domain.ProjectTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTagRepository extends JpaRepository<ProjectTag, Long> {

}