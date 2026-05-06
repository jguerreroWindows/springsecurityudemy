package com.semana3java.springjwt.Security.Filter;

import com.semana3java.springjwt.Security.Services.JwtUtil;
import com.semana3java.springjwt.Security.Services.UserDetailsServicesImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServicesImp userDetailsServicesImp;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHader = request.getHeader("Authorization");
        String username= null;
        String jwt = null;

        if (StringUtils.hasText(authorizationHader) && authorizationHader.startsWith("Bearer ")) {
            jwt = authorizationHader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }
        if (username !=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsServicesImp.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt,userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
