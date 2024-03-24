package com.product.k22.config;

import com.product.k22.service.UserService;
import com.product.k22.service.impl.UserSecurityDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                            request.requestMatchers("/home",
                                            "/login").permitAll()
                                    .requestMatchers("http://localhost:8080/register"
                                            , "http://localhost:8080/register-role").hasAnyRole("ADMIN")
                                    .requestMatchers("http://localhost:8080/customer/**",
                                            "http://localhost:8080/product/**").hasAnyRole("MANAGER",
                                            "ADMIN").anyRequest().authenticated()

                ).formLogin(f -> f.loginPage("/login").defaultSuccessUrl("/home").permitAll())
                .logout(l -> l.logoutUrl("/logout").permitAll());
        return http.build();

    }

    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();

    }

    @Autowired
    private UserSecurityDetailService service;

    @Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(service);
    }
}
