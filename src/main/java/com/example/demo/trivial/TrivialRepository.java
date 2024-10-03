package com.example.demo.trivial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrivialRepository extends JpaRepository<TrivialCardEntity, Long> {
    
}
