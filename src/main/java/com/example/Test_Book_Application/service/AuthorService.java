package com.example.Test_Book_Application.service;

import com.example.Test_Book_Application.model.Author;

import java.util.Optional;

public interface AuthorService {
    Author getAuthorById(Long id);
    Optional<Author> getAuthorByName(String name);
}
