package com.example.demo.library.books;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class BookDto implements IBookResponse {
    
    @Pattern(regexp = "^[0-9]{3}-[0-9]{10}$")
    private String isbn;


    @Length(min = 1, max = 100)
    private String title;


    @Length(min = 1, max = 100)
    private String author;

    // long text
    @Length(max = 10000)
    private String description;

    @Positive
    private int pages;
    
}
