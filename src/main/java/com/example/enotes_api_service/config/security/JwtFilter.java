package com.example.enotes_api_service.config.security;

import com.example.enotes_api_service.handler.GenericResponse;
import com.example.enotes_api_service.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
               // try {
                    // Ensure method name matches your JwtService method name
                    username = jwtService.extractUserName(token);
                //} catch (Exception e) {
                //    logger.error("Invalid JWT Token: " + e.getMessage());
                //}
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }catch(Exception e){
            /**
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write(e.getMessage());
            return;
             **/
            try {
                generateResponseError(response,e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            return;
        }
        filterChain.doFilter(request, response);
    }
    private void generateResponseError(HttpServletResponse response,Exception e) throws Exception{

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Object error = GenericResponse.builder()
                .status("failed")
                .message(e.getMessage())
                .responseStatus(HttpStatus.UNAUTHORIZED)
                .build()
                .create()
                .getBody();
        response.getWriter().write(new ObjectMapper().writeValueAsString(error));
    }
}