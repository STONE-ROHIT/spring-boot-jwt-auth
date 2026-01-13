package com.practice.spring_security.filter;

import com.practice.spring_security.service.JwtService;
import com.practice.spring_security.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ApplicationContext context;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{

        // it has a Http request Object, because it will accept a request from the user
        // it has a Http response Object to send a response back to user
        // also has a filter chain Object as it must know which filter it need to delicate next

        String authHeader = request.getHeader("Authorization");
        String jwToken = null;
        String username = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            jwToken = authHeader.substring(7);
            username = jwtService.extractUserName(jwToken);
        }

        //validation
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetail = context.getBean(UserService.class).loadUserByUsername(username);
            if(jwtService.validateToken(jwToken, userDetail)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken); // setting token in contest holder
            }
        }
        filterChain.doFilter(request, response);
    }
}
