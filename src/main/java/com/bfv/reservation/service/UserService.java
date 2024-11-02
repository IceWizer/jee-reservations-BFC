package com.bfv.reservation.service;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.repository.car.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends GlobalService<User, UserRepository> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    public Optional<User> getUserByEmail(String email) {
        return getRepository().findByEmail(email);
    }
}
