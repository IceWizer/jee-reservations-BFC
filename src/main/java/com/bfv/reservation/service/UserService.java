package com.bfv.reservation.service;

import com.bfv.reservation.model.domain.User;
import com.bfv.reservation.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends GlobalService<User, UserRepository> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    public Optional<User> findByEmail(String email) {
        return getRepository().findByEmail(email);
    }

    public List<String> getEmails() {
        return getRepository().findAll().stream().map(User::getEmail).toList();
    }
}
