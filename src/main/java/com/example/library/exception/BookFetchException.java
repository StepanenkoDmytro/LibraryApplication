package com.example.library.exception;

public class BookFetchException extends RuntimeException {
    public BookFetchException(String format) {
        super(format);
    }
}
