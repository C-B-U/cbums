package com.cbums.config;

import com.cbums.service.EncryptionService;
import com.cbums.type.UserRoleType;
import com.cbums.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;
    private final EncryptionService encryptionService;


    @Override
    public void configure(WebSecurity web) throws Exception {
        // static 디렉터리의 하위 파일 항상통과
        web.ignoring().antMatchers("/scripts/**", "/styles/**" , "/img/**" , "/lib/**" );
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
        auth.userDetailsService(memberService)
                .passwordEncoder(encryptionService);
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                // 페이지 권한 설정
                //잠시 테스트 때문에   .antMatchers("/admin/**").hasRole(UserRoleType.ADMIN.name())
                //잠시 테스트 때문에   .antMatchers("/study/**").hasRole(UserRoleType.MEMBER.name())
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll()


        // 로그인 설정
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/login-success")
                .permitAll()
        //로그아웃 설정

                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout")
                .invalidateHttpSession(true)
        //403 예외처리
                .and().exceptionHandling()
                .accessDeniedPage("/denied");
                // 403 예외처리 핸들링


    }


}
