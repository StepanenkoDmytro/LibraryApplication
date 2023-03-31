package com.example.Test_Book_Application.controller;

import com.example.Test_Book_Application.model.Book;
import com.example.Test_Book_Application.service.AuthorService;
import com.example.Test_Book_Application.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/v1/library")
public class BookController {
    private final BookService bookService;

    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("")
    public String listBooks(
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam Optional<Integer> page,
            Model model) {
        int currentPage = page.orElse(1);
        Pageable pageable = PageRequest.of(currentPage - 1,5);

        Page<Book> allBooks;
        if (filter != null && !filter.isEmpty()) {
            allBooks = bookService.findByPartOfTitle(filter, pageable);
        } else {
            allBooks = bookService.getAllBooks(pageable);
        }

        model.addAttribute("allBooks", allBooks);

        model.addAttribute("filter", filter);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", allBooks.getTotalPages());
        model.addAttribute("totalItems", allBooks.getTotalElements());

        return "library";
    }
}
