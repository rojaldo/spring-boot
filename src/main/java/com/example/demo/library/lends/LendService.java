package com.example.demo.library.lends;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.library.books.BookDto;
import com.example.demo.library.books.BookEntity;
import com.example.demo.library.books.BooksRespository;
import com.example.demo.library.books.BooksService;
import com.example.demo.library.users.UserRepository;
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
    private UserRepository usersRepository;

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

}
