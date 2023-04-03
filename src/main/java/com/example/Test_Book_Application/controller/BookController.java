package com.example.Test_Book_Application.controller;

import com.example.Test_Book_Application.model.Author;
import com.example.Test_Book_Application.model.Book;
import com.example.Test_Book_Application.service.AuthorService;
import com.example.Test_Book_Application.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        Pageable pageable = PageRequest.of(currentPage - 1, 6);

        Page<Book> allBooks = StringUtils.isEmpty(filter) ?
                bookService.getAllBooks(pageable) : bookService.findByPartOfTitle(filter, pageable);

        model.addAttribute("allBooks", allBooks);
        model.addAttribute("filter", filter);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", allBooks.getTotalPages());
        model.addAttribute("totalItems", allBooks.getTotalElements());

        return "library";
    }

    @GetMapping("/create")
    public String createBook() {
        return "book-add";
    }

    @PostMapping("/create")
    public String createBook(@RequestParam("file") MultipartFile file,
                           @RequestParam("title") String title,
                           @RequestParam("author") String author,
                           @RequestParam("year") int year) throws IOException {
        Author authorOfBook = authorService.getAuthorByName(author)
                .orElseGet(() -> new Author(author.trim()));

        Book book = new Book();
        book.setTitle(title);
        book.setYear(year);

        authorOfBook.addBook(book);
        bookService.saveBook(book, file);
        return "redirect:/api/v1/library";
    }

    @PostMapping("/{id}")
    public String removeBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/api/v1/library";
    }
}
