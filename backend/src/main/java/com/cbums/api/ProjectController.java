package com.cbums.api;

import com.cbums.config.auth.LoginUser;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.project.dto.*;
import com.cbums.core.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("")
    public ResponseEntity<List<ProjectResponse>> getProjects() {
        return ResponseEntity.ok(projectService.findAll());
    }

    @GetMapping("/{seq}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable("seq") Long projectId) {

        ProjectResponse result = projectService.findProject(projectId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<Void> createProject(@LoginUser SessionUser user,
                                              @Valid @RequestBody CreateProjectRequest createProjectRequest) {

        Long result = projectService.createProject(user, createProjectRequest);
        return ResponseEntity.created(URI.create("/project/" + result)).build();
    }

    @PatchMapping("/{seq}")
    public ResponseEntity<Void> updateProject(@LoginUser SessionUser user,
                                              @PathVariable("seq") Long projectId,
                                              @Valid @RequestBody CreateProjectRequest createProjectRequest) {

        projectService.updateProject(user, projectId, createProjectRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/recruit/{seq}")
    public ResponseEntity<Void> updateRecruit(@LoginUser SessionUser user,
                                              @PathVariable("seq") Long projectId) {
        projectService.updateRecruit(user, projectId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/finish/{seq}")
    public ResponseEntity<Void> finishProject(@LoginUser SessionUser user,
                                              @PathVariable("seq") Long projectId) {
        projectService.finishProject(user, projectId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{seq}/apply")
    public ResponseEntity<Void> applyProject(@LoginUser SessionUser user,
                                             @PathVariable("seq") Long projectId) {

        projectService.applyProject(user, projectId);
        return ResponseEntity.created(URI.create("/project/" + projectId)).build();
    }

    @DeleteMapping("/{seq}/apply")
    public ResponseEntity<Void> cancelApply(@LoginUser SessionUser user,
                                            @PathVariable("seq") Long projectId) {

        projectService.cancelApply(user, projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{seq}/apply")
    public ResponseEntity<List<ApplyProjectMemberResponse>> findApplyMember(@LoginUser SessionUser user,
                                                                            @PathVariable("seq") Long projectId) {
        List<ApplyProjectMemberResponse> result =
                projectService.findApplyMember(user, projectId);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/apply/{seq}/sign-up")
    public ResponseEntity<Void> updateSignUpType(@LoginUser SessionUser user,
                                                 @PathVariable("seq") Long projectMemberId,
                                                 @RequestBody ProjectSignUpTypeRequest signUpType) {

        projectService.updateSignUpType(user,projectMemberId,signUpType);
        return ResponseEntity.created(URI.create("/project/")).build();
    }

    @PatchMapping("/apply/{seq}/role")
    public ResponseEntity<Void> updateRoleType(@LoginUser SessionUser user,
                                                 @PathVariable("seq") Long projectMemberId,
                                                 @RequestBody ProjectRoleTypeRequest roleType) {

        projectService.updateRoleType(user,projectMemberId,roleType);
        return ResponseEntity.created(URI.create("/project/")).build();
    }
}
