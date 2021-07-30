package com.cbums.service;

import com.cbums.model.Member;
import com.cbums.type.UserRoleType;
import com.cbums.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public Member joinForWriteForm(Member member) {
        memberRepository.save(member);
        return member;
    }

    public Long getAcceptMember(String email) {

        Optional<Member> member = memberRepository.findByEmail(email);
        member.orElseThrow(NullPointerException::new);
        // 함수형 프로그래밍으로 변환 TODO
        if(member.get().getUserRoleType() == UserRoleType.VISITANT) {
            // throw

        }
        //세션으로 넘길 때 암호화를 해야하나...? 일단 보류 TODO
        return member.get().getMemberId();
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
