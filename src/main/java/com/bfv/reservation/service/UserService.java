package com.bfv.reservation.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.repository.UserRepository;

@Service
public class UserService extends GlobalService<User, UserRepository> {

    public UserService(UserRepository repository) {
        super(repository);
    }

    public Optional<User> findByEmail(String email) {
        return getRepository().findByEmail(email);
    }
}
