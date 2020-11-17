package com.gittx.demo.exception_handlers;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private Integer code;
    public CustomException(String message, Integer code)
    {
        super(message);
        this.code=code;

    }
}