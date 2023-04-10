package com.example.library.service.impl;

import com.example.library.exception.AuthorFetchException;
import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
    public Optional<Author> getAuthorByName(String name) {
        return authorRepository.findAuthorByName(name);
    }
}
