package com.creativedesignproject.kumoh_board_backend.Config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.creativedesignproject.kumoh_board_backend.Auth.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configurable
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource()))
                .csrf(CsrfConfigurer::disable) // 사이트 요청에 대한걸 어떻게 처리할 거냐
                .httpBasic(HttpBasicConfigurer::disable) // http 기본 설정을 사용하지 않겠다
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/", "/api/v1/auth/**", "/api/v1/search/**", "/file/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/**", "/api/v1/board/{category_id}/**", "/api/v1/category", "/api/v1/crawling/**").permitAll()
                        .requestMatchers("/api/v1/category/**").hasRole("ADMIN")
                        .requestMatchers("/api/v1/board/**", "/api/v1/auth/changePassword", "/api/v1/auth/changeNickname").hasRole("USER")
                        .anyRequest().authenticated())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new FailedAuthenticationEntryPoint()))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/v1/auth/**", corsConfiguration);
        source.registerCorsConfiguration("/api/v1/user/**", corsConfiguration);
        source.registerCorsConfiguration("/file/**", corsConfiguration);
        source.registerCorsConfiguration("/api/v1/board/**", corsConfiguration);
        source.registerCorsConfiguration("/api/v1/search/**", corsConfiguration);
        source.registerCorsConfiguration("/api/v1/category/**", corsConfiguration);
        source.registerCorsConfiguration("/api/v1/crawling/**", corsConfiguration);

        return source;
    }
}
