package com.example.demo.trivial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/v1/trivial")
public class TrivialRestController {

    @Autowired
    private TrivialService trivialService;

    @GetMapping("/cards")
    public ResponseEntity<List<ICardResponse>> getMethodName(@RequestParam(name = "amount", required = false, defaultValue = "1") int amount) {
        return ResponseEntity.status(HttpStatus.OK).body(this.trivialService.getCards(amount));
    }

    @PostMapping("/cards")
    public ResponseEntity<ICardResponse> postMethodName(@RequestBody TrivialCardDto card) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.trivialService.createCard(card));
    }
    
    
    
}
