package com.example.library.repository;

import com.example.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Page<Book> findAll(Pageable pageable);
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:partOfTitle%")
    Page<Book> findByPartOfTitle(@Param("partOfTitle") String partOfTitle, Pageable pageable);

}
