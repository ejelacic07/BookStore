package com.example.demo.dto;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Book isbn cannot be blank")
    private String isbn;

    @Positive(message = "Number of pages cannot be blank")
    private int pages;

    @DecimalMin(value = "0.0", message = "Price must be positive")
    private BigDecimal price;

    private Publisher publisher;


    public BookDTO(Book book) {
        this.title = book.getTitle();
        this.isbn = book.getIsbn();
        this.pages = book.getPages();
        this.price = book.getPrice();
        this.publisher = book.getPublisher();
    }



}
