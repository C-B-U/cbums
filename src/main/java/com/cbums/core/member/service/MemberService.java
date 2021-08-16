package com.cbums.core.member.service;

import com.cbums.common.exceptions.AuthException;
import com.cbums.common.exceptions.ErrorCode;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.core.member.dto.MemberAddDetailRequest;
import com.cbums.core.member.dto.SignUpRequest;
import com.cbums.model.SecurityUser;
import com.cbums.service.EncryptionService;
import com.cbums.service.NaverMailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HttpServletRequest request;
    private final NaverMailSendService naverMailSendService;
    private final EncryptionService encryptionService;

    public Member registerMember(SignUpRequest signUpRequest) {

        checkExistMember(signUpRequest);

        Member member = buildMember(signUpRequest);
        Member result = memberRepository.save(member);
        //Session 역할을 Controll로 위임해야 TODO
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("form-writer-id", result.getMemberId());
        return result;
    }

    private void checkExistMember(SignUpRequest signUpRequest) {
        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AuthException(ErrorCode.DUPLICATED_EMAIL);
        }

        if (memberRepository.existsByClassNumber(signUpRequest.getClassNumber())) {
            throw new AuthException(ErrorCode.DUPLICATED_CLASS_NUMBER);
        }

        if (memberRepository.existsByNickName(signUpRequest.getNickName())) {
            throw new AuthException(ErrorCode.DUPLICATED_NICK_NAME);
        }
    }

    private Member buildMember(SignUpRequest signUpRequest) {
        return Member.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .nickName(signUpRequest.getNickName())
                .department(signUpRequest.getDepartment())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .build();
    }

    public void addDetails(Member member, MemberAddDetailRequest memberAddDetailRequest) {
        member.setPassword(encryptionService.encode(memberAddDetailRequest.getPassword()));
        member.setIntroduce(memberAddDetailRequest.getIntroduce());
        member.setProfileImage(memberAddDetailRequest.getProfileImage());
        memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(NullPointerException::new);
        SecurityUser securityUser = new SecurityUser();
        securityUser.setUsername(member.getEmail());
        securityUser.setPassword(member.getPassword());

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        switch (member.getUserRoleType().name()) {
            case "ADMIN":
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            case "MEMBER":
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
            default:
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_VISITED"));
                break;
        }

        securityUser.setAuthorities(grantedAuthorities);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("login-user", member.getMemberId());
        return securityUser;
    }
}
