package com.example.library.service.impl;

import com.example.library.exception.BookFetchException;
import com.example.library.model.Book;
import com.example.library.model.Image;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getBookById(@NotNull Long id) {

        return bookRepository.findById(id).orElseThrow(() ->
                new BookFetchException(String.format("Book with id = %d not found", id)));
    }

    @Override
    public Page<Book> findByPartOfTitle(String partOfTitle, Pageable pageable) {
        return bookRepository.findByPartOfTitle(partOfTitle, pageable);
    }

    @Override
    public void saveBook(Book book, MultipartFile file) throws IOException {
        Image image;
        if (file.getSize() != 0) {
            image = toImageEntity(file);
            book.setImage(image);
        }
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        book.getAuthor().removeBook(book);
        bookRepository.deleteById(id);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setSize(file.getSize());
        image.setOriginFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        return image;
    }
}
