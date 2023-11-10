package com.flashmart.order.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id) {
        super("Order with ID " + id + " not found");
    }
}

