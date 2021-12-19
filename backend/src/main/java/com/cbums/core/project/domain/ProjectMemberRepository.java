package com.cbums.core.project.domain;

import com.cbums.core.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    boolean existsByMemberAndProject(Member member, Project project);

    List<ProjectMember> findAllByProject(Project project);

    void deleteByMemberAndProject(Member member, Project project);
}