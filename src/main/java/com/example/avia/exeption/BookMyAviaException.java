package com.example.avia.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BookMyAviaException extends Exception{
    public BookMyAviaException(Throwable t, String msg) {
        super(msg, t);
    }

    public BookMyAviaException(Throwable t) {
        super(t);
    }

    public BookMyAviaException(String msg) {
        super(msg);
    }
}
