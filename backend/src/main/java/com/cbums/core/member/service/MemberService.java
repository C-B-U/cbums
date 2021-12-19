package com.cbums.core.member.service;

import com.cbums.common.exceptions.AuthException;
import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.member.domain.*;
import com.cbums.core.member.dto.*;
import com.cbums.core.tag.Service.TagService;
import com.cbums.core.tag.domain.Tag;
import com.cbums.core.tag.dto.TagApplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberDetailRepository memberDetailRepository;
    private final MemberTagRepository memberTagRepository;

    private final TagService tagService;

    @Transactional
    public Long addDetails(SessionUser user, MemberAddDetailRequest memberAddDetailRequest) {
        MemberDetail result = memberDetailBuilder(user, memberAddDetailRequest);
        memberDetailRepository.save(result);
        Member member = findByName(user.getName());
        member.setMemberDetail(result);
        return member.getMemberId();
    }

    private MemberDetail memberDetailBuilder(SessionUser user, MemberAddDetailRequest memberAddDetailRequest) {
        Member member = findByName(user.getName());
        return MemberDetail.builder()
                .member(member)
                .name(memberAddDetailRequest.getName())
                .phoneNumber(memberAddDetailRequest.getPhoneNumber())
                .department(memberAddDetailRequest.getDepartment())
                .build();

    }

    @Transactional
    public void resign(SessionUser user) {
        MemberDetail memberDetail = findByMemberName(user.getName());
        memberDetail.setResign(true);
    }


    public void updateNickName(SessionUser user, UpdateNickNameRequest updateNickNameRequest) {
        checkDuplicatedNickName(updateNickNameRequest.getNickName());
        MemberDetail memberDetail = findByMemberName(user.getName());
        memberDetail.setNickName(updateNickNameRequest.getNickName());
    }
    public void updateIntroduce(SessionUser user, UpdateIntroduceRequest updateIntroduceRequest) {
        MemberDetail memberDetail = findByMemberName(user.getName());
        memberDetail.setIntroduce(updateIntroduceRequest.getIntroduce());
    }
    private void checkDuplicatedNickName(String nickName) {
        if (memberDetailRepository.existsByNickName(nickName)) {
            throw new AuthException(ErrorCode.DUPLICATED_NICK_NAME);
        }
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findAll() {
        List<MemberDetail> members = memberDetailRepository.findAll();
        return Collections.unmodifiableList(MemberResponse.listOf(members));
    }

    @Transactional(readOnly = true)
    public List<MemberResponseForAdmin> findAllForAdmin() {
        List<MemberDetail> members = memberDetailRepository.findAll();
        return Collections.unmodifiableList(MemberResponseForAdmin.listOf(members));
    }

    @Transactional(readOnly = true)
    public MemberResponse findMember(Long memberId) {
        MemberDetail member = findByMemberId(memberId);
        return MemberResponse.of(member);
    }

    @Transactional(readOnly = true)
    public MemberResponseForAdmin findMemberForAdmin(Long memberId) {
        MemberDetail member = findByMemberId(memberId);
        return MemberResponseForAdmin.of(member);
    }



    private Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

    public Member findByName(String name) {
        return memberRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

    private MemberDetail findByMemberId(Long memberId) {
        Member member = findById(memberId);
        return memberDetailRepository.findMemberDetailsByMemberId(member.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

    private MemberDetail findByMemberName(String memberName) {
        Member member = findByName(memberName);
        return memberDetailRepository.findMemberDetailsByMemberId(member.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

    @Transactional
    public void delete(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Transactional
    public void updateRole(Long memberId, UpdateRoleTypeRequest updateRoleTypeRequest) {
        Member member = findById(memberId);
        UserRoleType role = UserRoleType.MEMBER;;
        switch (updateRoleTypeRequest.getUserRoleType()) {
            case "GUEST" :
                role = UserRoleType.GUEST;
                break;
            case "ADMIN" :
                role = UserRoleType.ADMIN;
                break;
        }
        member.setRole(role);
    }

    @Transactional
    public void applyMemberTag(SessionUser user, TagApplyRequest req) {
        Member member = findByName(user.getName());
        List<Tag> tags = tagService.getTagsById(req);
        for(Tag t : tags) {
            memberTagRepository.save(new MemberTag(member, t));
        }
    }


}
