package com.example.blog2.Config;

import com.example.blog2.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Config {
private final MyUserDetailsService myUserDetailsService;


@Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

@Bean
public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
    http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/user/register","/api/v1/blog/getAll","/api/v1/blog/get-blog-by-title","/api/v1/blog/get-blog-by-id/").permitAll()
            .requestMatchers("/api/v1/blog/add","/api/v1/blog/update/","/api/v1/blog/delete/","/api/v1/blog/getAllMyBlogs","/api/v1/user/update").hasAuthority("USER")
            .requestMatchers("/api/v1/user/getAllUser","/api/v1/user/delete/").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
            .logout().logoutUrl("/api/v1/logout")
            .deleteCookies("JSESSIONID=650C6CFC9A0975E836EDB67940FB8935; Path=/; HttpOnly;")
            .invalidateHttpSession(true)
            .and()
            .httpBasic();
    return http.build();
}

}
