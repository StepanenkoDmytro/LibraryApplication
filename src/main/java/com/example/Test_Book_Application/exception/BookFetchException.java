package com.example.Test_Book_Application.exception;

public class BookFetchException extends RuntimeException {
    public BookFetchException(String format) {
        super(format);
    }
}
