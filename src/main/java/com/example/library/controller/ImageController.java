package com.example.library.controller;

import com.example.library.exception.ImageNotFoundException;
import com.example.library.model.Image;
import com.example.library.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;

    @GetMapping("/images/{id}")
    public ResponseEntity<?> getImageId(@PathVariable Long id) throws IOException {

        Image image = imageRepository.findById(id).orElseThrow(
                () -> new ImageNotFoundException(String.format("Image with id = %d not found", id))
        );
        return ResponseEntity.ok()
                .header("fileName", image.getOriginFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }

}
