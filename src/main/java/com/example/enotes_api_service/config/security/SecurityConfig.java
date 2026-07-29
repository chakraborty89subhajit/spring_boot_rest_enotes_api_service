package com.example.enotes_api_service.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf->csrf.disable())
                //session menagement to privent loging using old session password
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //permit only matching urls==no auth needed
                .authorizeHttpRequests(req->
                        req.antMatchers("/api/v1/home/**","/api/v1/auth/**","/api/v1/user/**")
                                .permitAll()
                                //otherwise auth needed
                                .anyRequest()
                                .authenticated())
                //for api based login httpBasic
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    //create a bean of BCryptPasswordEncoder
   @Bean
    public BCryptPasswordEncoder encoder(){
    return new BCryptPasswordEncoder();
    }

    //create  a password Enoder for password =1234
  //  @Bean
    //public PasswordEncoder encoder(){
      //  return NoOpPasswordEncoder.getInstance();
    //}


    //create DB level authentication provider
    @Bean
            public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder());
       // provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;

    }
@Bean
public AuthenticationManager authenticationManaget(
        AuthenticationConfiguration authenticationConfiguration)
throws Exception
{
    return authenticationConfiguration.getAuthenticationManager();
}
}
