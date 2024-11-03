package com.bfv.reservation.exception;

public class DuplicateElement extends RuntimeException {
    public DuplicateElement(String message) {
        super(message + " already exist");
    }
}
