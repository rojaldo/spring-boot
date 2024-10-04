package com.example.demo.library.lends;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.errors.ErrorDto;
import com.example.demo.library.books.BookDto;
import com.example.demo.library.books.BookEntity;
import com.example.demo.library.books.BooksRespository;
import com.example.demo.library.books.BooksService;
import com.example.demo.library.users.LibraryUserRepository;
import com.example.demo.library.users.UsersService;
import com.example.demo.library.users.UserDto;
import com.example.demo.library.users.UserEntity;


@Service
public class LendService {

    @Autowired
    private LendRepository lendRepository;

    @Autowired
    private BooksRespository booksRepository;

    @Autowired
    private LibraryUserRepository usersRepository;

    @Autowired
    private BooksService bookService;

    @Autowired
    private UsersService usersService;

    public LendDto getLend(long id) {
        LendEntity lend = lendRepository.findById(id);
        return null;

    }

    public Iterable<LendDto> getLends() {
        Iterable<LendEntity> lends = lendRepository.findAll();
        Iterable<LendDto> response = new ArrayList<>();
        for (LendEntity lend : lends) {
            BookDto book = (BookDto) bookService.getBookById(lend.getBook().getId());
            UserDto user = (UserDto) usersService.getUserById(lend.getUser().getId());
            String lendDate = lend.getLendDate();
            String returnDate = lend.getDueDate();
            LendDto lendDto = LendDto.builder()
                    .id(lend.getId())
                    .book(book)
                    .user(user)
                    .lendDate(lendDate)
                    .dueDate(returnDate).build();
            ((ArrayList<LendDto>) response).add(lendDto);
        }

        return response;
    }

    public LendDto addLend(LendRequest lend) {
        BookEntity book = booksRepository.findById(lend.getBookId()).get();
        UserEntity user = usersRepository.findById(lend.getUserId()).get();
        lendRepository.save(LendEntity.builder()
                .book(book)
                .user(user)
                .lendDate(lend.lendDate)
                .dueDate(lend.dueDate)
                .build());

        return LendDto.builder()
                .book((BookDto)bookService.getBookById(lend.getBookId()))
                .user((UserDto)usersService.getUserById(lend.getUserId()))
                .lendDate(lend.lendDate)
                .dueDate(lend.dueDate)
                .build();
    }

    public ILendResponse updateLend(Long id, LendRequest lend) {
        Optional<LendEntity> lendEntity = lendRepository.findById(id);
        if (lendEntity.isEmpty()) {
            return ErrorDto.builder()
                    .message("Lend not found")
                    .status(404)
                    .build();
        }
        LendEntity lendToUpdate = lendEntity.get();
        lendToUpdate.setLendDate(lend.lendDate);
        lendToUpdate.setDueDate(lend.dueDate);
        lendToUpdate.setBook(this.booksRepository.findById(lend.getBookId()).get());
        lendToUpdate.setUser(this.usersRepository.findById(lend.getUserId()).get());
        lendToUpdate.setStatus(lend.getStatus());
        lendRepository.save(lendToUpdate);
        return LendDto.builder()
                .book((BookDto)bookService.getBookById(lendToUpdate.getBook().getId()))
                .user((UserDto)usersService.getUserById(lendToUpdate.getUser().getId()))
                .lendDate(lendToUpdate.getLendDate())
                .dueDate(lendToUpdate.getDueDate())
                .build();
    }

    public ILendResponse deleteLend(Long id) {
        Optional<LendEntity> lendEntity = lendRepository.findById(id);
        if (lendEntity.isEmpty()) {
            return ErrorDto.builder()
                    .message("Lend not found")
                    .status(404)
                    .build();
        }
        lendRepository.delete(lendEntity.get());
        return LendDto.builder().
                id(id)
                .build();
    }

}
