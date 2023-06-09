package com.example.library.service;

import com.example.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BookService {
    Page<Book> getAllBooks(Pageable pageable);
    Book getBookById(Long id);
    Page<Book> findByPartOfTitle(String partOfTitle,Pageable pageable);

    void saveBook(Book book, MultipartFile file) throws IOException;
    void deleteBook(Long id);
}
