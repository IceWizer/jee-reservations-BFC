package com.bfv.reservation.controller;

import com.bfv.reservation.model.domain.PersistentEntity;
import com.bfv.reservation.model.response.BasicResponse;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;

import java.util.List;

public class BuilderResponse<T extends PersistentEntity> {
    protected ListResponse<T> getListResponse(List<T> list) {
        return ListResponse.<T>builder()
                .count(list.size())
                .data(list)
                .build();
    }

    protected DataResponse<T> getDataResponse(T entity, String name) {
        return DataResponse.<T>builder()
                .message(name + " found")
                .data(entity)
                .build();
    }

    protected BasicResponse save(String message) {
        return BasicResponse.builder()
                .message(message)
                .build();
    }

    protected BasicResponse delete(String message) {
        return BasicResponse.builder()
                .message(message)
                .build();
    }
}