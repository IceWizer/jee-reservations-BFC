package com.bfv.reservation.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User utilisateur = this.repository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas"));

        return org.springframework.security.core.userdetails.User.withUsername(email)
                .password(utilisateur.getPassword())
                .build();
    }
}
