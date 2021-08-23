package com.cbums.core.member.service;

import com.cbums.common.exceptions.AccessException;
import com.cbums.common.exceptions.AuthException;
import com.cbums.common.exceptions.EntityNotFoundException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.config.auth.dto.SessionUser;
import com.cbums.core.member.domain.*;
import com.cbums.core.member.dto.*;
import com.cbums.common.security.EncryptionService;
import com.cbums.common.util.NaverMailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberDetailRepository memberDetailRepository;

    @Transactional
    public void addDetails(SessionUser user, MemberAddDetailRequest memberAddDetailRequest) {
        memberDetailRepository.save(memberDetailBuilder(user, memberAddDetailRequest));
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
        Member member = findByName(user.getName());
        MemberDetail memberDetail =
                memberDetailRepository.findAnswersByFormId(member.getMemberId())
                        .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));

        memberDetail.setResign(true);
        memberDetailRepository.save(memberDetail);
    }

    private void checkDuplicatedNickName(String nickName) {
        if (memberDetailRepository.existsByNickName(nickName)) {
            throw new AuthException(ErrorCode.DUPLICATED_NICK_NAME);
        }
    }

    //update TODO

    @Transactional(readOnly = true)
    public List<MemberResponse> findAll() {
        List<Member> members = memberRepository.findAll();
        return Collections.unmodifiableList(MemberResponse.listOf(members));
    }

    @Transactional(readOnly = true)
    public MemberResponse findMember(Long memberId) {
        Member member = findById(memberId);
        return MemberResponse.of(member);
    }

    //member 조회 response 변경 TODO
    private Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

    private Member findByName(String name) {
        return memberRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.NOT_FOUNDED_ID));
    }

    @Transactional
    public void delete(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Transactional
    public void updateRole(Long memberId, UpdateRoleTypeRequest updateRoleTypeRequest) {
        Member member = findById(memberId);
        member.setRole(updateRoleTypeRequest.getUserRoleType());
        memberRepository.save(member);
    }


}
