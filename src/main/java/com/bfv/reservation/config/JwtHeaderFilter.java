package com.bfv.reservation.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.getToken(request);

        if (token != null && JwtUtil.isValid(token, userService)) {
            List<GrantedAuthority> authorities = new ArrayList<>();

            User currentUser = userService.findByEmail(JwtUtil.getEmail(token)).get();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + currentUser.getRole().toUpperCase()));

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    currentUser.getEmail(),
                    null,
                    authorities
            );

            // On ajoute l'authentification au contexte de Sécurité de Spring Security
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Passer à la suite de la chaine de filtres
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || authHeader.length() <= 7) {
            return null;
        }

        // Bearer letoken
        return authHeader.substring(7);
    }
}
