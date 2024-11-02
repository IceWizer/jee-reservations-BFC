package com.bfv.reservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bfv.reservation.model.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByEmail(String email);
}
