package com.example.Test_Book_Application.repository;

import com.example.Test_Book_Application.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
