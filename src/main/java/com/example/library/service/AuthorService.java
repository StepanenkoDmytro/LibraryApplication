package com.example.library.service;

import com.example.library.model.Author;

import java.util.Optional;

public interface AuthorService {
    Author getAuthorById(Long id);
    Optional<Author> getAuthorByName(String name);
}
