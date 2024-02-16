package com.springboot.exercise.config;

import com.springboot.exercise.serviceImpl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService myUserDetailsService() {
        return new UserServiceImpl();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers(HttpMethod.GET, "/",
                            "/login",
                            "/error",
                            "/login-error",
                            "logout",
                            "/css/**",
                            "/js/**"
                            ).permitAll();
                    authConfig.requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN");
                    authConfig.requestMatchers(HttpMethod.GET, "/student").hasRole("STUDENT");
                    authConfig.requestMatchers(HttpMethod.GET, "/user").hasAnyRole("USER", "ADMIN");
                    authConfig.requestMatchers(HttpMethod.GET, "/role").hasAnyRole("ADMIN", "STUDENT");
                })
                .formLogin(login -> {
                    login.loginPage("/login");
                    login.defaultSuccessUrl("/", true);
                    login.failureUrl("/login-error");
                })
                .logout(logout -> {
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                    logout.logoutSuccessUrl("/");
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                });
        return http.build();
    }
}
