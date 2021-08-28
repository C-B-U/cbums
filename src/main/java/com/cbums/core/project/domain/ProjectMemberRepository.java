package com.cbums.core.project.domain;

import com.cbums.core.member.domain.Member;
import com.cbums.core.project.domain.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    boolean existsByMemberAndProject(Member member, Project project);
}