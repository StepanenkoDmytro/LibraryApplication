package com.example.Test_Book_Application.exception;

public class AuthorFetchException extends RuntimeException {
    public AuthorFetchException(String format) {
        super(format);
    }
}
