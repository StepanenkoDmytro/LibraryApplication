package com.example.Test_Book_Application.service;

import com.example.Test_Book_Application.model.Author;

public interface AuthorService {
    Author getAuthorById(Long id);
    Author getAuthorByName(String name);
}
