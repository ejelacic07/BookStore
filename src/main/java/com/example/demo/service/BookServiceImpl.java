package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.dto.BookDTO;
import com.example.demo.repository.SpringDataBookRepository;
import com.example.demo.repository.SpringDataPublisherRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private SpringDataBookRepository bookRepository;
    private SpringDataPublisherRepository publisherRepository;


    @Override
    public List<BookDTO> findAll() {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookRepository.findAll()) {
            bookDTOList.add(convertBookToBookDTO(book));
        }
        return bookDTOList;
    }


    @Override
    public Optional<BookDTO> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn).stream()
                .map(this::convertBookToBookDTO)
                .toList().stream().findFirst();

    }

    @Override
    public Optional<BookDTO> save(BookDTO bookDTO) {

        return Optional.of(convertBookToBookDTO(bookRepository.save(convertBookDtoToBook(bookDTO))));
    }



    @Override
    public Optional<BookDTO> update(String isbn, BookDTO updatedBookDTO) {

        Optional<Book> bookToUpdate = bookRepository.findByIsbn(isbn);

        if (bookToUpdate.isPresent()) {
            Book book = bookToUpdate.get();

            book.setPublisher(updatedBookDTO.getPublisher());
            book.setPrice(updatedBookDTO.getPrice());
            book.setPages(updatedBookDTO.getPages());
            book.setIsbn(updatedBookDTO.getIsbn());
            book.setTitle(updatedBookDTO.getTitle());

            Book updatedBook = bookRepository.save(book);
            return Optional.of(convertBookToBookDTO(updatedBook));
        }
        return Optional.empty();
    }


    @Override
    public void deleteByIsbn(String isbn) {
        Optional<Book> optionalBook = bookRepository.findByIsbn(isbn);
        if (optionalBook.isPresent()) {
            bookRepository.deleteByIsbn(isbn);
        }
    }

    private BookDTO convertBookToBookDTO(Book book) {
        return new BookDTO(book.getTitle(),
                book.getIsbn(), book.getPages(),
                book.getPrice(), book.getPublisher());
    }


    private Book convertBookDtoToBook(BookDTO bookDTO) {
        Book book = new Book();
        //   book.setId(latestId);
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setPages(bookDTO.getPages());
        book.setPrice(bookDTO.getPrice());
        book.setPublisher(bookDTO.getPublisher());

        return book;
    }
}
