package com.muneesh.config;

import com.muneesh.Services.CustomUserDetails;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.swing.text.StringContent;

@Component
@RequiredArgsConstructor //Automatically creates a constructor to the final filed in the class
// we have to implement this onceperrequestfilter because we want to filter the request only once per request
// It ensures the token is checked only once per single request, not multiple times within the same request.
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final Jwt jwtservice;
    private final CustomUserDetails details;


 //Filterchain is used to pass the request and response to the next filterin the chain
@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filter) throws IOException, ServletException, java.io.IOException {

    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
     final String jwt;
     final String username;

    if(header == null || !header.startsWith("Bearer ")){
         filter.doFilter(request,response);
         return;

    }
     jwt = header.substring(7); // remove "Bearer "
     username = this.jwtservice.extractUsername(jwt);
     if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
         //SecurityContextHolder is a spring security class itr holds information about the current login details
         //getContext gets the current security context holder details
         //getAuthentication checks if user is authenticated checked or not
         UserDetails userdetails = details.loadUserByUsername(username);

         if(jwtservice.isTokenValid(jwt,userdetails)){
             UsernamePasswordAuthenticationToken  authenticationToken =
                     new UsernamePasswordAuthenticationToken(
                             userdetails,
                             null,
                             userdetails
                                     .getAuthorities());
             SecurityContextHolder.getContext().setAuthentication(authenticationToken);


         }


     }
    filter.doFilter(request, response);


    }




}
