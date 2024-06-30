package com.creativedesignproject.kumoh_board_backend.Auth.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.creativedesignproject.kumoh_board_backend.Auth.entity.UserEntity;
import com.creativedesignproject.kumoh_board_backend.Auth.provider.JwtProvider;
import com.creativedesignproject.kumoh_board_backend.mapper.AuthMapper;

import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final AuthMapper authMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = parseBearerToken(request);
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String userId = jwtProvider.validate(token); // 토큰 검증이 되었다면 UserId를 꺼내온다 만약 아니라면 다음 필터로 진행
            if (userId == null) {
                filterChain.doFilter(request, response);
                return;
            }

            UserEntity userEntity = authMapper.findByUserId(userId);
            String role = userEntity.getRole(); // role : ROLE_USER, ROLE_ADMIN // 역할을 부여할 경우에는 접두사 ROLE_를 꼭 붙여줘야된다.

            //ROLE_DEVLEOPER, ROLE_ADMIN, ROLE_USER
            List<GrantedAuthority> authorities = new ArrayList<>(); // 권한이 여러개일 경우 List를
            authorities.add(new SimpleGrantedAuthority(role)); // 권한을 넣어준다

            AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, authorities); // 토큰을 만들어서
        
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(securityContext);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        boolean hasAuthorization = StringUtils.hasText(authorization); // 실제 값이 있는지 확인해주는 친구
        if (!hasAuthorization)
            return null;

        boolean isBearer = authorization.startsWith("Bearer "); // Bearer 인증 방식이 맞는지 아닌지 확인
        if (!isBearer)
            return null;

        String token = authorization.substring(7); // Bearer 뒤에 있는 토큰을 가져오기 위해서 자르기
        return token;
    }
}
