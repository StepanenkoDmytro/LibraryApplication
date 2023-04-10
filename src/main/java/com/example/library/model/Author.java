package com.example.library.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;
    @Column(name = "author_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "author")
    private List<Book> books;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public void addBook(Book book){
        if(books == null){
            books = new ArrayList<>();
        }
        books.add(book);
        book.setAuthor(this);
    }
    public void removeBook(Book book){
        books.remove(book);
        book.setAuthor(null);
    }
}
