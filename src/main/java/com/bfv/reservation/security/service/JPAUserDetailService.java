package com.bfv.reservation.security.service;

import com.bfv.reservation.exception.NotFound;
import com.bfv.reservation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.bfv.reservation.Library.USER;

@Service
@RequiredArgsConstructor
public class JPAUserDetailService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.bfv.reservation.model.domain.User user = repository.findByEmail(email).orElseThrow(() -> new NotFound(USER, email));

        return User.withUsername(email)
                .password(user.getPassword())
                .roles(user.isAdmin() ? "ADMIN" : "USER")
                .build();
    }
}
