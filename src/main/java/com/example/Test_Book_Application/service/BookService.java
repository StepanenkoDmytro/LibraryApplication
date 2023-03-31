package com.example.Test_Book_Application.service;

import com.example.Test_Book_Application.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Page<Book> getAllBooks(Pageable pageable);
    Book getBookById(Long id);
    Page<Book> findByPartOfTitle(String partOfTitle,Pageable pageable);

//    Page<Book> getAllBooksBy10(Integer page);
}
