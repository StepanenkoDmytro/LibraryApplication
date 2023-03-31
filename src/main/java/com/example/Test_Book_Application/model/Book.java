package com.example.Test_Book_Application.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "books")
@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "book_title")
    private String title;

    @Column(name = "book_year")
    private int year;

    @ManyToOne(fetch = FetchType.EAGER,
    cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

//    public void addImageToBook(Image image){
//        if(images == null){
//            images = new ArrayList<>();
//        }
//        images.add(image);
//        image.setBook(this);
//    }
}
