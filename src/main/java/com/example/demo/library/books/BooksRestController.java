package com.example.demo.library.books;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.errors.ErrorDto;

@RestController
@RequestMapping("/api/v1/library")
public class BooksRestController {

    @Autowired
    BooksService booksService;

    @GetMapping("/books")
    public ResponseEntity<Iterable<IBookResponse>> getBooks(BookDto book) {
        return ResponseEntity.ok().body(this.booksService.getAllBooks(book));
    }

    @PostMapping("/books")
    public ResponseEntity<IBookResponse> createBook(@RequestBody @Validated BookDto book) {
        Optional<IBookResponse> result = this.booksService.createBook(book);
        if (result.get() instanceof ErrorDto) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body((ErrorDto) result.get());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result.get());
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<IBookResponse> modifyBook(@RequestBody @Validated BookDto book, @PathVariable String isbn) {
        Optional<IBookResponse> result = this.booksService.modifyBook(book, isbn);
        if (result.get() instanceof ErrorDto) {
            ErrorDto error = (ErrorDto) result.get();
            return ResponseEntity.status(error.getStatus()).body(error);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @PatchMapping("/books/{isbn}")
    public ResponseEntity<IBookResponse> updateBook(@RequestBody @Validated BookDto book, @PathVariable String isbn) {
        Optional<IBookResponse> result = this.booksService.updateBook(book, isbn);
        if (result.get() instanceof ErrorDto) {
            ErrorDto error = (ErrorDto) result.get();
            return ResponseEntity.status(error.getStatus()).body(error);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result.get());
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<IBookResponse> deleteBook(@PathVariable String isbn) {
        Optional<IBookResponse> response = this.booksService.deleteBookByIsbn(isbn);
        if (response.get() instanceof ErrorDto) {
            ErrorDto userError = (ErrorDto) response.get();
            return ResponseEntity.status(userError.getStatus()).body(userError);
        }
        else {
            return ResponseEntity.status(200).body(response.get());
        }
    }
    
}
