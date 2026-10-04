package com.example.demo.controller;


import com.example.demo.dto.BookDTO;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll().stream().toList());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<List<BookDTO>> findBooksByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.findByIsbn(isbn).stream().toList());
    }


    @PostMapping
    public ResponseEntity<?> saveNewBook(@Valid @RequestBody BookDTO bookDTO) {
        Optional<BookDTO> saveBook = bookService.save(bookDTO);
        return ResponseEntity.ok(saveBook);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody BookDTO bookDTO, @PathVariable String isbn) {

        Optional<BookDTO> bsOptional = bookService.update(isbn, bookDTO);
        if (bsOptional.isPresent()) {
            return ResponseEntity.ok(bookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
         if(bookService.findByIsbn(isbn).isPresent()) {
             bookService.deleteByIsbn(isbn);
             return new ResponseEntity<>(HttpStatus.OK);
         } else {
             return ResponseEntity.notFound().build();
         }
    }

}