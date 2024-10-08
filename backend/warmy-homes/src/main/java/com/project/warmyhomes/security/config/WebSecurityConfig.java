package com.project.warmyhomes.security.config;

import com.project.warmyhomes.security.jwt.AuthEntryPointJwt;
import com.project.warmyhomes.security.jwt.AuthTokenFilter;
import com.project.warmyhomes.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    private final AuthEntryPointJwt unauthorizedHandler;

    /**
     * main configuration class for spring security
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                //we are specifying the exception handling for unauthorized login try
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                //we are not keeping the session info in backend.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //we are disabling security for some URL.s
                .authorizeRequests().antMatchers(AUTH_WHITE_LIST).permitAll()
                //The rest URL.s will be authenticated
                .anyRequest().authenticated();

        //when you logged-in. other request should come from the same origin.
        http.headers().frameOptions().sameOrigin();
        //injecting the authentication provider that we will use
        http.authenticationProvider(daoAuthenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * this will be our authentication manager in spring security
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * @return our own token filter class that exist in jwt package
     */
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //all urls are allowed
                registry.addMapping("/**")
                        //all origins are allowed, different servers or domains
                        .allowedOrigins("*")
                        //all header contents are allowed (Authorization, Content-Type)
                        .allowedHeaders("*")
                        //POST-GET-PUT-DELETE ..... every request types is allowed
                        .allowedMethods("*");
            }
        };
    }

    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "swagger-ui.html",
            "/swagger-ui/**",
            "index.html",
            "/images/**",
            "/css/**",
            "/js/**",
            "/address/**",


            "/adverts",
            "/adverts/cities",
            "/adverts/categories",
            "/adverts/popular",
            "/adverts/{slug}",

            "/images/{imageId}",
            "/images",

            "/categories",
            "/categories/{id}",
            "/categories/{slug}",

            "/advert-types/**",

            "/users/login",
            "/users/register",
            "/users/forgot-password",
            "/users/reset-password",
            "/contact-messages",
    };
}
