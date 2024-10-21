package com.bfv.reservation.exception;

public class ElementNotFound extends RuntimeException {
    public ElementNotFound(String id) {
        super("Element not found with this " + id);
    }
}
