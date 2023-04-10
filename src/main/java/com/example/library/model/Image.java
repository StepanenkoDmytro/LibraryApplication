package com.example.library.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    @Column(name = "image_name")
    private String name;
    @Column(name = "image_originFileName")
    private String originFileName;
    @Column(name = "image_size")
    private Long size;
    @Column(name = "image_contentType")
    private String contentType;
    @Lob
    private byte[] bytes;
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "image")
    private Book book;
}
