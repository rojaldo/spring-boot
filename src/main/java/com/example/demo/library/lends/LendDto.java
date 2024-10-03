package com.example.demo.library.lends;


import com.example.demo.library.books.BookDto;
import com.example.demo.library.users.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LendDto {

    Long id;

    BookDto book;

    UserDto user;

    @JsonProperty("lend_date")
    String lendDate;

    @JsonProperty("due_date")
    String dueDate;

    
    
}
