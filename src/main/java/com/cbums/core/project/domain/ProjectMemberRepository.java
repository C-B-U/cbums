package com.cbums.core.project.domain;

import com.cbums.core.member.domain.Member;
import com.cbums.core.project.domain.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    boolean existsByMemberAndProject(Member member, Project project);

    List<ProjectMember> findAllByProject(Project project);

    void deleteByMemberAndProject(Member member, Project project);
}