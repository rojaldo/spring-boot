package com.example.demo.library.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByName(String name);
    Optional<UserEntity> findByNameAndEmail(String name, String email);
    List<UserEntity> findByAgeGreaterThan(int age);
}
