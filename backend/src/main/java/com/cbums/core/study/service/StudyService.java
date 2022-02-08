package com.cbums.core.study.service;

import com.cbums.common.exceptions.AccessException;
import com.cbums.common.exceptions.ActionException;
import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.board.domain.StudyTagRepository;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.UserRoleType;
import com.cbums.core.member.service.MemberService;
import com.cbums.core.study.domain.*;
import com.cbums.core.study.dto.*;
import com.cbums.core.tag.Service.TagService;
import com.cbums.core.tag.domain.Tag;
import com.cbums.core.tag.dto.TagApplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyService {
    private final StudyRepository studyRepository;
    private final StudyMemberRepository studyMemberRepository;
    private final StudyTagRepository studyTagRepository;

    private final MemberService memberService;
    private final TagService tagService;

    @Transactional
    public Long createStudy(SessionUser user, CreateStudyRequest createStudyRequest) {
        Study study = buildStudy(createStudyRequest);
        study.setRegisterDatetime(LocalDateTime.now());
        Member producer = memberService.findByName(user.getName());
        study.setProducer(producer);

        return studyRepository.save(study).getStudyId();
    }

    private Study buildStudy(CreateStudyRequest createStudyRequest) {
        return new Study().builder()
                .name(createStudyRequest.getName())
                .maximumMember(createStudyRequest.getMaximumMember())
                .startDate(createStudyRequest.getStartDate())
                .finishDate(createStudyRequest.getFinishDate())
                .rule(createStudyRequest.getRule())
                .additionalExplain(createStudyRequest.getAdditionalExplain())
                .build();
    }

    @Transactional(readOnly = true)
    public List<StudyResponse> findAll() {
        return StudyResponse.listOf(studyRepository.findAll());
    }

    @Transactional(readOnly = true)
    public StudyResponse findStudy(Long projectId) {
        return StudyResponse.of(findById(projectId));
    }

    private Study findById(Long projectId) {
        return studyRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

    @Transactional
    public void updateRecruit(SessionUser user, Long projectId) {
        Study study = findById(projectId);

        confirmMember(user, study.getProducer());

        if(study.getRecruit()) {
            study.setRecruit(false);
        }else {
            study.setRecruit(true);
        }

    }

    @Transactional
    public void updateStudy(SessionUser user, Long projectId, CreateStudyRequest createStudyRequest) {
        Study study = findById(projectId);

        confirmMember(user, study.getProducer());

        study.setName(createStudyRequest.getName());
        study.setMaximumMember(createStudyRequest.getMaximumMember());

    }
    public void finishStudy(SessionUser user, Long projectId) {
        Study study = findById(projectId);

        confirmMember(user, study.getProducer());

        study.setFinished(true);
    }

    private void confirmMember(SessionUser user, Member member) {
        if (member.getName() != user.getName() &&
                member.getRole() != UserRoleType.ADMIN) {
                throw new AccessException(ErrorCode.USER_BAD_ACCESS);
        }
    }

    //projectMember

    @Transactional
    public Long applyStudy(SessionUser user, Long projectId) {
        Member member = memberService.findByName(user.getName());
        Study study = findById(projectId);
        if(studyMemberRepository.existsByMemberAndStudy(member, study)) {
            throw new ActionException(ErrorCode.ALREADY_DONE);
        }

        StudyMember studyMember = StudyMember.builder()
                .member(member)
                .study(study)
                .build();

        return studyMemberRepository.save(studyMember).getStudyMemberId();
    }

    @Transactional
    public void cancelApply(SessionUser user, Long projectId) {
        Member member = memberService.findByName(user.getName());
        Study study = findById(projectId);

        studyMemberRepository.deleteByMemberAndStudy(member, study);
    }

    @Transactional(readOnly = true)
    public List<ApplyStudyMemberResponse> findApplyMember(SessionUser user, Long projectId) {
        Study study = findById(projectId);
        confirmMember(user, study.getProducer());
        List<StudyMember> result = studyMemberRepository.findAllByStudy(study);

        return ApplyStudyMemberResponse.listof(result);
    }

    @Transactional
    public void updateSignUpType(SessionUser user, Long projectMemberId, StudySignUpTypeRequest signUpTypeRequest) {
        StudyMember studyMember = findStudyMemberById(projectMemberId);
        confirmMember(user, studyMember.getStudy().getProducer());
        StudySignUpType signUpType = StudySignUpType.UNDEFINED;
        switch (signUpTypeRequest.getProjectSignUpType()) {
            case "ALLOW":
                signUpType = StudySignUpType.ALLOW;
                break;
            case "REFUSE":
                signUpType = StudySignUpType.REFUSE;
                break;
        }
        studyMember.setSignUpType(signUpType);
    }

    @Transactional
    public void updateRoleType(SessionUser user, Long projectMemberId, StudyRoleTypeRequest roleTypeRequest) {
        StudyMember studyMember = findStudyMemberById(projectMemberId);
        confirmMember(user, studyMember.getStudy().getProducer());

        StudyRoleType roleType = StudyRoleType.MEMBER;
        switch (roleTypeRequest.getStudyRoleType()) {
            case "LEADER":
                roleType = StudyRoleType.LEADER;
                break;
            case "MENTOR":
                roleType = StudyRoleType.MENTOR;
                break;
            case "CLERK":
                roleType = StudyRoleType.CLERK;
                break;
        }
        studyMember.setStudyRoleType(roleType);
    }

    private StudyMember findStudyMemberById(Long projectMemberId){
        return studyMemberRepository.findById(projectMemberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

    public void applyStudyTag(Long projectId, TagApplyRequest req) {
        Study study = findById(projectId);
        List<Tag> tags = tagService.getTagsById(req);
        for(Tag t : tags) {
            studyTagRepository.save(new StudyTag(study, t));
        }
    }



}