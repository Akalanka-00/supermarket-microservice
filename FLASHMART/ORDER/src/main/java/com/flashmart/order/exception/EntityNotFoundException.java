package com.flashmart.order.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("Order with ID " + id + " not found");
    }
}

