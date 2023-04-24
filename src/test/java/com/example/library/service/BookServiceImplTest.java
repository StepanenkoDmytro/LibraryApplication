package com.example.library.service;

import com.example.library.exception.BookFetchException;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.Image;
import com.example.library.repository.BookRepository;
import com.example.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;
    private Book book;

    private Image image;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor(new Author());
        image = new Image();
        image.setBytes(new byte[0]);
        image.setContentType("image/jpeg");
        image.setName("test-image.jpg");
        image.setOriginFileName("test-image.jpg");
        image.setSize(12345L);
    }

    @Test
    void getAllBooks() {
        Page<Book> bookPage = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        when(bookRepository.findAll(pageable)).thenReturn(bookPage);

        assertEquals(bookPage, bookService.getAllBooks(pageable));
    }

    @Test
    void getBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        assertEquals(book, bookService.getBookById(1L));
        assertThrows(BookFetchException.class, () -> bookService.getBookById(2L));
    }

    @Test
    void findByPartOfTitle() {
        Page<Book> bookPage = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        when(bookRepository.findByPartOfTitle("Test", pageable)).thenReturn(bookPage);
        assertEquals(bookPage, bookService.findByPartOfTitle("Test", pageable));
    }

    @Test
    void saveBook() throws IOException {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test-image.jpg", "image/jpeg", new byte[0]);
        bookService.saveBook(book, file);
        verify(bookRepository, times(1)).save(book);
    }
}
