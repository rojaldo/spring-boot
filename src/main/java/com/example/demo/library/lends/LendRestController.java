package com.example.demo.library.lends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/library")
@Transactional
public class LendRestController {

    @Autowired
    private LendService lendService;


    // @RequestMapping(path = "/lends", method = RequestMethod.GET)
    @GetMapping("/lends")
    public ResponseEntity<Iterable<LendDto>> getLends() {
        Iterable<LendDto> lends = lendService.getLends();
        return ResponseEntity.ok().body(lends);
        
    }

    @PostMapping("/lends")
    public ResponseEntity<LendDto> addLend(@RequestBody LendRequest lend) {
        LendDto lendDto = lendService.addLend(lend);
        return ResponseEntity.ok().body(lendDto);
        // return ResponseEntity.ok().body(LendDto.builder().build());
    }


    
}
