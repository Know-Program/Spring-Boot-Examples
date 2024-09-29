package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("sam")
                .password(passwordEncoder.encode("sam"))
                .roles("ADMIN")
                .build();
        UserDetails employee = User.builder()
                .username("syed")
                .password(passwordEncoder.encode("syed"))
                .roles("EMPLOYEE")
                .build();
        UserDetails student = User.builder()
                .username("william")
                .password(passwordEncoder.encode("william"))
                .roles("STUDENT")
                .build();
        return new InMemoryUserDetailsManager(admin, employee, student);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/home").permitAll()
                .requestMatchers("/welcome").authenticated()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/emp").hasRole("EMPLOYEE")
                .requestMatchers("/std").hasRole("STUDENT")
                .requestMatchers("/common").hasAnyRole("ADMIN", "EMPLOYEE")
                .anyRequest().authenticated()
             )
            // Login Form details
            .formLogin(form -> form // default login form
                    .defaultSuccessUrl("/welcome", true)
                    .permitAll()
            ) 
            // Logout details
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            )
            // Exception Details
            .exceptionHandling(exception -> exception
                    // custom page to show access denied message
                    .accessDeniedPage("/access-denied")
            );
        return http.build();
    }

}
