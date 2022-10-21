package com.example.avia.exeption;

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
