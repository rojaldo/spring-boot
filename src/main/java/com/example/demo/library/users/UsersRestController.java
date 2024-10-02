package com.example.demo.library.users;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/library")
public class UsersRestController {

    @Autowired
    private UsersService usersService;
    
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(this.usersService.getUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<IUserResonse> createUser(@RequestBody @Validated UserDto user) {
        IUserResonse response = this.usersService.createUser(user);
        if (response instanceof UserErrorDto) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((UserErrorDto) response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body((UserDto) response);
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<IUserResonse> deleteUser(@PathVariable Long id) {
        IUserResonse response = this.usersService.deleteUser(id);
        if (response instanceof UserErrorDto) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((UserErrorDto) response);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    
}
