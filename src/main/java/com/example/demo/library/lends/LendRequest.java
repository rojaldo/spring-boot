package com.example.demo.library.lends;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LendRequest {

    @JsonProperty("book_id")
    long bookId;

    @JsonProperty("user_id")
    long userId;

    @JsonProperty("lend_date")
    String lendDate;

    @JsonProperty("due_date")
    String dueDate;

}
