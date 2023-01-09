package com.korit.library.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring()
               .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//       스태틱폴더에 대한 시큐리티 로그인을 하지 않아도 된다.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable(); //우리가 만든 로그인페이지를 쓰겠다
        http.authorizeRequests()
                .antMatchers("/mypage/**","/secuirty/**")// mypage로 들어오면 인증이 필요하게 만듦
                .authenticated()
                .anyRequest() // 다른 페이지들은 모든 권한을 줘라.
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/account/login") //로그인 페이지 get 요청
                .loginProcessingUrl("/account/login")
                .defaultSuccessUrl("/index");
    }
}
