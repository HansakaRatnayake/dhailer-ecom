package com.eCom.dhailer.config;

import com.eCom.dhailer.jwt.JwtUserNameAndPasswordAuthenticationFilter;
import com.eCom.dhailer.jwt.JwtTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.crypto.SecretKey;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenVerifier jwtTokenVerifier;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;
    private final UserDetailsService userDetailsService;


    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, JwtTokenVerifier jwtTokenVerifier, SecretKey secretKey, JwtConfig jwtConfig, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenVerifier = jwtTokenVerifier;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Content-type","Authorization","Cache-Control"));
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        JwtUserNameAndPasswordAuthenticationFilter authenticationFilter = new JwtUserNameAndPasswordAuthenticationFilter(authenticationManager, jwtConfig, secretKey, userDetailsService);

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors->cors.configurationSource(request -> corsConfiguration))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(authenticationFilter)
                .addFilterAfter(jwtTokenVerifier, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(
                                "/api/v1/"
                        )
                        .permitAll()
                        .anyRequest().authenticated())
                .build();


    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

}