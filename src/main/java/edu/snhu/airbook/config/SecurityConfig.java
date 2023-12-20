package edu.snhu.airbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Spring security password encoder
     * @return new BCryptPasswordEncoder object
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Filter chain
     * @param http Http Security
     * @return SecurityFilterChain
     * @throws Exception exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .securityMatcher("/login", "/signup", "/bookflight", "/", "", "/home")
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("POST", "GET").permitAll())
                .securityMatcher("/**")
                .authorizeHttpRequests(authorize->authorize.anyRequest().permitAll());
        return http.build();
    }
}
