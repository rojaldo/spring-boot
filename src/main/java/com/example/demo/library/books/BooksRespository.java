package com.example.demo.library.books;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRespository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findById(long id);
    Optional<BookEntity> findByIsbn(String isbn);
    List<BookEntity> findByTitleContainingIgnoreCase(String title);
    List<BookEntity> findByAuthorContainingIgnoreCase(String author);
    BookEntity findByAuthorAndTitle(String author, String title);

}
