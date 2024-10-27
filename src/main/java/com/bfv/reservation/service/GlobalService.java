package com.bfv.reservation.service;

import com.bfv.reservation.model.domain.PersistentEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Getter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class GlobalService<T extends PersistentEntity, R extends JpaRepository<T, String>> {
    private final R repository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public Optional<T> findById(String id) {
        return repository.findById(id);
    }

    public String save(T object) {
        repository.save(object);

        return object.getId();
    }

    public String delete(T object) {
        String id = object.getId();
        repository.delete(object);

        return id;
    }

    public String delete(String id) {
        repository.deleteById(id);

        return id;
    }
}