package com.example.demo.library.lends;


import org.springframework.data.jpa.repository.JpaRepository;

public interface LendRepository extends JpaRepository<LendEntity, Long> {

    LendEntity findById(long id);
}
