package com.cbums.config.auth;

import com.cbums.core.member.service.MemberService;
import com.cbums.common.security.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 항상통과
        web.ignoring().antMatchers("/scripts/**", "/styles/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                // 페이지 권한 설정
                //잠시 테스트 때문에   .antMatchers("/admin/**").hasRole(UserRoleType.ADMIN.name())
                //잠시 테스트 때문에   .antMatchers("/study/**").hasRole(UserRoleType.MEMBER.name())
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                //로그아웃 설정
                .and().logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);


    }


}
