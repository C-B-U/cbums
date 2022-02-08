package com.cbums.api;

import com.cbums.config.auth.LoginUser;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.study.dto.*;
import com.cbums.core.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @GetMapping("")
    public ResponseEntity<List<StudyResponse>> getStudies() {
        return ResponseEntity.ok(studyService.findAll());
    }

    @GetMapping("/{seq}")
    public ResponseEntity<StudyResponse> getStudy(@PathVariable("seq") Long studyId) {

        StudyResponse result = studyService.findStudy(studyId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<Void> createStudy(@LoginUser SessionUser user,
                                            @Valid @RequestBody CreateStudyRequest createStudyRequest) {

        Long result = studyService.createStudy(user, createStudyRequest);
        return ResponseEntity.created(URI.create("/project/" + result)).build();
    }

    @PatchMapping("/{seq}")
    public ResponseEntity<Void> updateStudy(@LoginUser SessionUser user,
                                            @PathVariable("seq") Long studyId,
                                            @Valid @RequestBody CreateStudyRequest createStudyRequest) {

        studyService.updateStudy(user, studyId, createStudyRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/recruit/{seq}")
    public ResponseEntity<Void> updateRecruit(@LoginUser SessionUser user,
                                              @PathVariable("seq") Long studyId) {
        studyService.updateRecruit(user, studyId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/finish/{seq}")
    public ResponseEntity<Void> finishStudy(@LoginUser SessionUser user,
                                            @PathVariable("seq") Long studyId) {
        studyService.finishStudy(user, studyId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{seq}/apply")
    public ResponseEntity<Void> applyStudy(@LoginUser SessionUser user,
                                           @PathVariable("seq") Long studyId) {

        studyService.applyStudy(user, studyId);
        return ResponseEntity.created(URI.create("/study/" + studyId)).build();
    }

    @DeleteMapping("/{seq}/apply")
    public ResponseEntity<Void> cancelApply(@LoginUser SessionUser user,
                                            @PathVariable("seq") Long studyId) {

        studyService.cancelApply(user, studyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{seq}/apply")
    public ResponseEntity<List<ApplyStudyMemberResponse>> findApplyMember(@LoginUser SessionUser user,
                                                                          @PathVariable("seq") Long studyId) {
        List<ApplyStudyMemberResponse> result =
                studyService.findApplyMember(user, studyId);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/apply/{seq}/sign-up")
    public ResponseEntity<Void> updateSignUpType(@LoginUser SessionUser user,
                                                 @PathVariable("seq") Long studyMemberId,
                                                 @RequestBody StudySignUpTypeRequest signUpType) {

        studyService.updateSignUpType(user,studyMemberId,signUpType);
        return ResponseEntity.created(URI.create("/project/")).build();
    }

    @PatchMapping("/apply/{seq}/role")
    public ResponseEntity<Void> updateRoleType(@LoginUser SessionUser user,
                                                 @PathVariable("seq") Long studyMemberId,
                                                 @RequestBody StudyRoleTypeRequest roleType) {

        studyService.updateRoleType(user,studyMemberId,roleType);
        return ResponseEntity.created(URI.create("/study/")).build();
    }
}
