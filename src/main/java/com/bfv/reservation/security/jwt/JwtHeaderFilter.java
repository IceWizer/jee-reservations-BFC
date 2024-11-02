package com.bfv.reservation.security.jwt;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.repository.car.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.bfv.reservation.Library.USER;

@Component
@RequiredArgsConstructor
public class JwtHeaderFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Implementation of the filter
        String token = request.getHeader("Authorization");

        if (token != null) {
            token = token.substring(7);
            Optional<String> email = JwtUtil.getEmail(token);

            if (email.isPresent()) {
                User user = userRepository.findByEmail(email.get()).orElseThrow(() -> new NotFound(USER, email.get()));

                Authentication authentication = new UsernamePasswordAuthenticationToken(email.get(), null, List.of(new SimpleGrantedAuthority(user.getRole())));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
