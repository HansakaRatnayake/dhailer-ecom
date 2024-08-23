package com.eCom.dhailer.jwt;


import com.eCom.dhailer.config.JwtConfig;
import com.eCom.dhailer.repo.UserRepo;
import com.google.common.base.Strings;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@Component
public class JwtTokenVerifier extends OncePerRequestFilter {


    private final JwtConfig jwtConfig;

    private final JwtService jwtService;

    private final UserRepo userRepo;

    @Autowired
    public JwtTokenVerifier(JwtConfig jwtConfig, JwtService jwtService, UserRepo userRepo) {
        this.jwtConfig = jwtConfig;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (Strings.isNullOrEmpty(authorization) && !authorization.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.replace(jwtConfig.getTokenPrefix(),"");


        try {
            String email = jwtService.extractUserEmail(token);
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = jwtService.extractUserAuthorities(token);


            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            userRepo.findByEmail(email),
                            null,
                            simpleGrantedAuthorities
                    )
            );

        }catch (Exception e) {
            throw new IllegalStateException(String.format("failed to extract user from token: %s", token), e);
        }

        filterChain.doFilter(request, response);

    }



}
