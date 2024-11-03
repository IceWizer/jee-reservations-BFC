package com.bfv.reservation.controller;

import java.util.List;

import com.bfv.reservation.model.response.BasicResponse;
import com.bfv.reservation.model.response.DataResponse;
import com.bfv.reservation.model.response.ListResponse;

public class BuilderResponse<T> {

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

    protected BasicResponse save(String message, String id) {
        return BasicResponse.builder()
                .message(message + " saved")
                .id(id)
                .build();
    }

    protected BasicResponse delete(String message) {
        return BasicResponse.builder()
                .message(message)
                .build();
    }
}
