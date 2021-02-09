package study.boot.proj.util;

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
import study.boot.proj.service.MemberService;

@RequiredArgsConstructor
@EnableWebSecurity // 1
@Configuration
public class MemberConfig extends WebSecurityConfigurerAdapter { // 2

    private final MemberService userService; // 3

    @Bean
    public PasswordEncoder passwordEncoder() { // 4
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) { // 5
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // 6
        http
                .authorizeRequests() // 7
                .antMatchers("/login", "/signup", "/user").permitAll() // 누구나 접근 허용
                .antMatchers("/").hasRole("USER") // USER, ADMIN만 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .formLogin() // 8
                .loginPage("/login") // 로그인 페이지 링크
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                .and()
                .logout() // 9
                .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) // 세션 날리기
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 10
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder()); // 해당 서비스(userService)에서는 UserDetailsService를 implements해서 loadUserByUsername() 구현해야함 (서비스 참고)
    }
}
/*
    1. Spring Security 활성화
    2. Spring Security 설정파일로서의 역할을 하기 위해 상속해야 하는 클래스
    3. 후에 사용할 유저 정보를 가져올 클래스 :: 프로그래머가 만드는 것
    4. 비밀번호를 암호화할 때 사용할 인코더를 미리 빈으로 등록해놓는 과정
    5. WebSecurityConfigurerAdapter 클래스를 상속받으면 사용할 수 있는 메소드. 인증을 무시할 경로들을 설정할 수 있음.
    6. WebSecurityConfigurerAdapter 클래스를 상속받으면 사용할 수 있는 메소드. http 관련 인증 설정이 가능함.
    7. 접근에 대한 인증 설정. 어떤 경로에 어떤 권한이 접근할 수 있는지 세부적으로 설정할 수 있다.
    8. 로그인에 관한 설정.
    9. 로그아웃에 관한 설정
    10. 로그인할 때 필요한 정보를 가져오는 곳. 유저 정보를 가져올 클래스를 설정하고 패스워드 인코더를 설정한다.
*/