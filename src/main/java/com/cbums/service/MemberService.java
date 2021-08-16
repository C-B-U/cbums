package com.cbums.service;

import com.cbums.common.util.NaverMailSendService;
import com.cbums.controller.postParameter.MemberDetailFormParameter;
import com.cbums.core.member.domain.Member;
import com.cbums.core.member.domain.MemberRepository;
import com.cbums.service.exception.CheckCodeNotEqualsException;
import com.cbums.service.exception.NotLoginedException;
import com.cbums.core.member.domain.UserRoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final HttpServletRequest request;
    private final NaverMailSendService naverMailSendService;
    private final EncryptionService encryptionService;

    public void logout() throws NotLoginedException {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("login-user") == null) throw new NotLoginedException();
        httpSession.removeAttribute("login-user");
    }

}
