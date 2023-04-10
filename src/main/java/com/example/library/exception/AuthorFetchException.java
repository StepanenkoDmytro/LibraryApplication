package com.example.library.exception;

public class AuthorFetchException extends RuntimeException {
    public AuthorFetchException(String format) {
        super(format);
    }
}
