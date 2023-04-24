package com.example.library.service;

import com.example.library.exception.AuthorFetchException;
import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void getAuthorById(){
        Long authorId = 1L;
        Author author = new Author();
        author.setId(authorId);
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        Author result = authorService.getAuthorById(authorId);

        assertEquals(author, result);
        verify(authorRepository).findById(authorId);
        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    void getAuthorByIdThrowsAuthorFetchException(){
        Long authorId = 1L;
        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        assertThrows(AuthorFetchException.class, () -> authorService.getAuthorById(authorId));
        verify(authorRepository).findById(authorId);
        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    void getAuthorByName(){
        String authorName = "Taras Shevchenko";
        Author author = new Author(authorName);

        when(authorRepository.findAuthorByName(authorName)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.getAuthorByName(authorName);

        assertTrue(result.isPresent());
        assertEquals(author, result.get());

        verify(authorRepository).findAuthorByName(authorName);
        verifyNoMoreInteractions(authorRepository);
    }

    @Test
    void getAuthorByNameNotFound(){
        String authorName = "Taras Shevchenko";
        Author author = new Author(authorName);
        when(authorRepository.findAuthorByName(authorName)).thenReturn(Optional.empty());

        Optional<Author> result = authorService.getAuthorByName(authorName);

        assertFalse(result.isPresent());
        verify(authorRepository).findAuthorByName(authorName);
        verifyNoMoreInteractions(authorRepository);
    }
}