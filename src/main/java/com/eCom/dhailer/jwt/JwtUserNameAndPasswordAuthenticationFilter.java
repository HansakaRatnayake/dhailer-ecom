package com.eCom.dhailer.jwt;


import com.eCom.dhailer.config.JwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;


public class JwtUserNameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final UserDetailsService userDetailsService;


    public JwtUserNameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserNameAndPasswordAuthenticationRequest userNameAndPasswordAuthentication = new ObjectMapper()
                    .readValue(request.getInputStream(), UserNameAndPasswordAuthenticationRequest.class);

            UserDetails userDetails = userDetailsService.loadUserByUsername(userNameAndPasswordAuthentication.getUserEmail());

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userNameAndPasswordAuthentication.getUserEmail(),
                    userNameAndPasswordAuthentication.getPassword()

            );

            return authenticationManager.authenticate(authenticationToken);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder()
                .subject(authResult.getName())
                .claim("authorities",authResult.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(secretKey)
                .compact();

        response.addHeader(HttpHeaders.AUTHORIZATION, jwtConfig.getTokenPrefix()+token);

    }
}
