package com.bfv.reservation.controller;

import com.bfv.reservation.exception.ElementNotFound;
import com.bfv.reservation.model.domain.PersistentEntity;
import com.bfv.reservation.model.response.BasicResponse;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;
import com.bfv.reservation.service.GlobalService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GlobalController<T extends PersistentEntity, S extends GlobalService<T, ?>> {
    private final S service;

    // Other methods
    protected ListResponse<T> getAll() {
        List<T> list = service.findAll();

        return ListResponse.<T>builder()
                .data(list)
                .count(list.size())
                .build();
    }

    protected DataResponse<T> getOne(String id) {
        T entity = service.findById(id).orElseThrow(() -> new ElementNotFound(id));

        return DataResponse.<T>builder()
                .message("Element found")
                .data(entity)
                .build();
    }

    protected BasicResponse save(T entity) {
        return BasicResponse.builder()
                .message(service.save(entity))
                .build();
    }

    protected BasicResponse delete(String id) {
        return BasicResponse.builder()
                .message(service.delete(id))
                .build();
    }


    protected S getService() {
        return service;
    }
}