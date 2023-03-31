package com.example.Test_Book_Application.service.impl;

import com.example.Test_Book_Application.exception.AuthorFetchException;
import com.example.Test_Book_Application.exception.BookFetchException;
import com.example.Test_Book_Application.model.Author;
import com.example.Test_Book_Application.repository.AuthorRepository;
import com.example.Test_Book_Application.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() ->
                new AuthorFetchException(String.format("Author with id = %d not found", id)));
    }

    @Override
    public Author getAuthorByName(String name) {
        return authorRepository.findAuthorByName(name).orElseThrow(() ->
                new AuthorFetchException(String.format("Author with name = %s not found", name)));
    }
}
