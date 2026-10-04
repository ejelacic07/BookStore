package com.example.demo.repository;

import com.example.demo.domain.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataBookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    @Transactional
    void deleteByIsbn(String isbn);

}
