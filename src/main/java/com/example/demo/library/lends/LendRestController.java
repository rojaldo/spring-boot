package com.example.demo.library.lends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.errors.ErrorDto;

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

    @PutMapping ("/lends/{id}")
    public ResponseEntity<ILendResponse> updateLend(@PathVariable Long id, @RequestBody LendRequest lend) {
        ILendResponse response = lendService.updateLend(id, lend);
        if (response instanceof ErrorDto) {
            return ResponseEntity.status(((ErrorDto) response).getStatus()).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping ("/lends/{id}")
    public ResponseEntity<ILendResponse> deleteLend(@PathVariable Long id) {
        ILendResponse response = lendService.deleteLend(id);
        if (response instanceof ErrorDto) {
            return ResponseEntity.status(((ErrorDto) response).getStatus()).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    
}
