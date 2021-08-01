package com.cbums.service;

import com.cbums.controller.postParameter.SignUpFormParameter;
import com.cbums.model.Member;
import com.cbums.model.SecurityUser;
import com.cbums.repository.FormRepository;
import com.cbums.service.exception.NotAcceptMemberException;
import com.cbums.type.UserRoleType;
import com.cbums.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HttpServletRequest request;

    public Member joinForWriteForm(Member member) {
        Member savedMember = memberRepository.save(member);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("form-writer-id", savedMember.getMemberId());
        return member;
    }

    public void checkAcceptMember(String email) throws NotAcceptMemberException {

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



    public List<Member> findMemberList() {
        return memberRepository.findAll();
    }

    public Member findMemberById(Long id) {
        return memberRepository.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).get();
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

    public void logout(){
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("login-user");
    }

}
