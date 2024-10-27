package com.bfv.reservation.exception;

public class NotFound extends RuntimeException {
    public NotFound(String id, String name) {
        super(name + " not found with this " + id);
    }
}
