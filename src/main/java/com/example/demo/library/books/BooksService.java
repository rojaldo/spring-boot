package com.example.demo.library.books;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.errors.ErrorDto;

import jakarta.transaction.Transactional;

@Service
public class BooksService {

    @Autowired
    BooksRespository booksRespository;

    public Optional<IBookResponse> createBook(BookDto book) {
        // search isbn book in the database
        Optional<BookEntity> be = this.booksRespository.findByIsbn(book.getIsbn());
        if (be.isPresent()) {
            return Optional.of(ErrorDto.builder().status(400).message("Book already exists").build());
        }
        BookEntity bookEntity = BookEntity.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .pages(book.getPages())
                .build();
        this.booksRespository.save(bookEntity);
        return Optional.of(this.getBookDto(bookEntity));
    }

    public IBookResponse getBookById(long id) {
        BookEntity be = this.booksRespository.findById(id).get();
        if (be == null) {
            return null;
        }
        return this.getBookDto(be);
    }

    public Iterable<IBookResponse> getAllBooks(BookDto book) {
        Iterable<BookEntity> bookEntities = this.booksRespository.findAll();
        if (book.getTitle() == null && book.getAuthor() == null) {
            
        }else if (book.getTitle() != null && book.getAuthor() == null) {
            bookEntities = this.booksRespository.findByTitleContainingIgnoreCase(book.getTitle());
        }else if (book.getTitle() == null && book.getAuthor() != null) {
            bookEntities = this.booksRespository.findByAuthorContainingIgnoreCase(book.getAuthor());
        }
        return this.getBookDtos
(bookEntities);
    }

    public IBookResponse deleteBookById (long id) {
        BookEntity be = this.booksRespository.findById(id).get();
        if (be == null) {
            return ErrorDto.builder().status(404).message("Book not found").build();
        }else {
            this.booksRespository.deleteById(id);
            return this.getBookDto(be);
        }
    }

    public Optional<IBookResponse> deleteBookByIsbn (String isbn) {
        Optional<BookEntity> obe = this.booksRespository.findByIsbn(isbn);
        if (obe.isEmpty()) {
            return Optional.of(ErrorDto.builder().status(404).message("Book not found").build());
        }else {
            BookEntity be = obe.get();
            this.booksRespository.delete(be);
            return Optional.of(this.getBookDto(be));
        }
    }

    public Optional<IBookResponse> modifyBook (BookDto book, long id){
        Optional<BookEntity> obe = this.booksRespository.findById(id);
        if (obe.isEmpty()) {
            return Optional.of(ErrorDto.builder().status(404).message("Book not found").build());
        }else {
            BookEntity be = obe.get();
            be.setIsbn(book.getIsbn());
            be.setTitle(book.getTitle());
            be.setAuthor(book.getAuthor());
            be.setDescription(book.getDescription());
            be.setPages(book.getPages());
            this.booksRespository.save(be);
            return Optional.of(this.getBookDto(be));
        }
    }

    public Optional<IBookResponse> modifyBook(BookDto book, String isbn) {
        Optional<BookEntity> obe = this.booksRespository.findByIsbn(isbn);
        if (obe.isEmpty()) {
            return Optional.of( ErrorDto.builder().status(404).message("Book not found").build());
        }
        BookEntity bookEntity = obe.get();
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setDescription(book.getDescription());
        bookEntity.setPages(book.getPages());
        this.booksRespository.save(bookEntity);
        return Optional.of(this.getBookDto(bookEntity));
    }

    public Optional<IBookResponse> updateBook (BookDto book, long id){
        Optional<BookEntity> obe = this.booksRespository.findById(id);
        if (obe.isEmpty()) {
            return Optional.of(ErrorDto.builder().status(404).message("Book not found").build());
        }else {
            BookEntity be = obe.get();
            if (book.getIsbn() != null) {
                be.setIsbn(book.getIsbn());
            }
            if (book.getTitle() != null) {
                be.setTitle(book.getTitle());
            }
            if (book.getAuthor() != null) {
                be.setAuthor(book.getAuthor());
            }
            if (book.getDescription() != null) {
                be.setDescription(book.getDescription());
            }
            if (book.getPages() != 0) {
                be.setPages(book.getPages());
            }
            this.booksRespository.save(be);
            return Optional.of(this.getBookDto(be));
        }
    }

    public Optional<IBookResponse> updateBook(BookDto book, String isbn) {
        Optional<BookEntity> be = this.booksRespository.findByIsbn(isbn);
        if (be.isEmpty()) {
            return Optional.of(ErrorDto.builder().status(404).message("Book not found").build());
        }
        BookEntity bookEntity = be.get();
        if (book.getTitle() != null) {
            bookEntity.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null) {
            bookEntity.setAuthor(book.getAuthor());
        }
        if (book.getDescription() != null) {
            bookEntity.setDescription(book.getDescription());
        }
        if (book.getPages() != 0) {
            bookEntity.setPages(book.getPages());
        }
        this.booksRespository.save(bookEntity);
        return Optional.of(this.getBookDto(bookEntity));
    }

    private Iterable<IBookResponse> getBookDtos(Iterable<BookEntity> bookEntities) {
        return StreamSupport.stream(bookEntities.spliterator(), false)
                .map(be -> this.getBookDto(be))
                .collect(Collectors.toList());
    }

    private IBookResponse getBookDto(BookEntity be) {
        return BookDto.builder()
                .isbn(be.getIsbn())
                .title(be.getTitle())
                .author(be.getAuthor())
                .description(be.getDescription())
                .pages(be.getPages())
                .build();
    }
    
}
