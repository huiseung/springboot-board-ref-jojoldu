package com.example.board.config.auth;


import com.example.board.domain.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable();
        http
                .authorizeRequests()
                .antMatchers("/", "/fragment/**","/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(UserRole.USER.name())  //update user set role='user'
                .anyRequest().authenticated();
        http
                .logout()
                .logoutSuccessUrl("/");
        http
                .oauth2Login()
                .defaultSuccessUrl("/home", true)
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}
