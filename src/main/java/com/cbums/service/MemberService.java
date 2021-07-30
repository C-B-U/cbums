package com.cbums.service;

import com.cbums.controller.postParameter.SignUpFormParameter;
import com.cbums.model.Member;
import com.cbums.repository.FormRepository;
import com.cbums.service.exception.NotAcceptMemberException;
import com.cbums.type.UserRoleType;
import com.cbums.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HttpServletRequest request;

    public Member joinForWriteForm(Member member) {
        memberRepository.save(member);
        return member;
    }

    public String getAcceptMember(String email) throws NotAcceptMemberException {

        Optional<Member> member = memberRepository.findByEmail(email);
        member.orElseThrow(NullPointerException::new);
        // 함수형 프로그래밍으로 변환 TODO
        if (member.get().getUserRoleType() == UserRoleType.VISITANT) {
            // throw
            throw new NotAcceptMemberException();
        }
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("accept-email", email);
        //세션으로 넘길 때 암호화를 해야하나...? 일단 보류 TODO

        return member.get().getEmail();
    }


    public void setMemberOtherInfo(SignUpFormParameter signUpFormParameter) {

        HttpSession httpSession = request.getSession();
        String email = (String)httpSession.getAttribute("accept-email");
        httpSession.removeAttribute("accept-email");

        Member member = memberRepository.findByEmail(email).get();
        String password = new BCryptPasswordEncoder().encode(signUpFormParameter.getPassword());
        memberRepository.setAcceptMember(member.getMemberId(),
                password,
                signUpFormParameter.getIntroduce(),
                signUpFormParameter.getImage());

    }



    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).get();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        switch (member.getUserRoleType()) {
            case ADMIN:
                grantedAuthorities.add(new SimpleGrantedAuthority(UserRoleType.ADMIN.name()));
            case MEMBER:
                grantedAuthorities.add(new SimpleGrantedAuthority(UserRoleType.MEMBER.name()));
            default:
                grantedAuthorities.add(new SimpleGrantedAuthority(UserRoleType.VISITANT.name()));
                break;
        }

        return new User(member.getEmail(), member.getPassword(), grantedAuthorities);
    }

}
