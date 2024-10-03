package com.example.demo.library.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByName(String name);
    Optional<UserEntity> findByNameAndEmail(String name, String email);
    List<UserEntity> findByAgeGreaterThan(int age);
    List<UserEntity> findByAgeGreaterThanAndAgeLessThan(int ageGt, int ageLt);
    List<UserEntity> findByNameIgnoreCaseAndAgeGreaterThanAndAgeLessThan(String name, int ageGt, int ageLt);
}
