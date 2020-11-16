package com.gittx.demo.exception_handlers;

public class ResourceAlreadyExists extends RuntimeException {

    public ResourceAlreadyExists(String message) {
        super(message);
    }
}