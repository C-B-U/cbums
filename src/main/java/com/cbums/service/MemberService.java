package com.cbums.service;

import com.cbums.controller.postParameter.MemberDetailFormParameter;
import com.cbums.core.member.domain.Member;
import com.cbums.model.SecurityUser;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.service.exception.CheckCodeNotEqualsException;
import com.cbums.service.exception.NotAcceptMemberException;
import com.cbums.service.exception.NotLoginedException;
import com.cbums.service.exception.OverlapDataException;
import com.cbums.core.member.domain.UserRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HttpServletRequest request;
    private final NaverMailSendService naverMailSendService;
    private final EncryptionService encryptionService;

    public Member registerMember(Member member) throws OverlapDataException {
        if(memberRepository.findByEmail(member.getEmail()).isPresent()
        || memberRepository.findByClassNumber(member.getClassNumber()).isPresent()) {
            throw new OverlapDataException();
        }
        Member savedMember = memberRepository.save(member);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("form-writer-id", savedMember.getMemberId());
        //만약에 중간에 나간 후에 다시 접속한다면...? TODO
        return member;
    }

    public void checkAcceptMember(String email) throws NotAcceptMemberException, MessagingException {

        Member member = memberRepository.findByEmail(email).orElseThrow(NullPointerException::new);
        if (member.getUserRoleType() == UserRoleType.VISITANT) {
            // throw
            throw new NotAcceptMemberException();
        }
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("accept-email", email);
        sendMailCertificationCode();

    }

    public void sendMailCertificationCode() throws MessagingException {
        HttpSession httpSession = request.getSession();
        Random random = new Random();
        Integer key = random.nextInt(99999999);
        //key 암호화 안해도 되나...? TODO
        httpSession.setAttribute("certify-key", key.toString());
        naverMailSendService.sendEmail(
                (String)httpSession.getAttribute("accept-email"),
                "씨부엉: 메일 인증 코드입니다",
                key.toString()
        );
    }

    public void checkMailCode(String code) throws CheckCodeNotEqualsException {
        HttpSession httpSession = request.getSession();
        if(code.equals(httpSession.getAttribute("accept-email"))) {
            httpSession.removeAttribute("accept-email");
        }else {
            throw new CheckCodeNotEqualsException();
        }
    }


    public String getBlindMemberEmail(Integer classNumber) {
        char[] memberEmail = memberRepository
                .findByClassNumber(classNumber)
                .orElseThrow(NullPointerException::new)
                .getEmail()
                .toCharArray();

        for (int i = 1; i < memberEmail.length; i += 2) {
            memberEmail[i] = '*';
        }

        return memberEmail.toString();
    }

    public void setTemporaryPassword(String email) {
        //임시 비밀번호 생성 후 email로 전송 TODO
    }

    public boolean compareMailCertificationCode(Integer inputCode) {
        HttpSession httpSession = request.getSession();
        if (inputCode == httpSession.getAttribute("certify-key")) {
            httpSession.removeAttribute("certify-key");
            return true;
        } else {
            return false;
        }
    }

    //param을 그대로 사용하는 것이 맞는 것일까...? TODO
    public Long setMemberDetail(MemberDetailFormParameter signUpFormParameter) {

        HttpSession httpSession = request.getSession();
        String email = (String) httpSession.getAttribute("accept-email");
        httpSession.removeAttribute("accept-email");

        Member member = memberRepository.findByEmail(email).get();
        String password = encryptionService.encode(signUpFormParameter.getPassword());
        memberRepository.updateMemberDetail(member.getMemberId(),
                password,
                signUpFormParameter.getIntroduce(),
                signUpFormParameter.getImage());

        return member.getMemberId();
    }

    public List<Member> findMemberList() throws NoSuchElementException {
        List<Member> memberList = memberRepository.findAll();
        if (memberList.isEmpty()) throw new NoSuchElementException();
        return memberRepository.findAll();
    }

    public Member findMemberById(Long id) {

        return memberRepository.findById(id).orElseThrow(NullPointerException::new);

    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public void updateMemberRoleType(Long id, UserRoleType userRoleType) {
        memberRepository.updateMemberRoleType(id,userRoleType);
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

    public void logout() throws NotLoginedException {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("login-user") == null) throw new NotLoginedException();
        httpSession.removeAttribute("login-user");
    }

}
