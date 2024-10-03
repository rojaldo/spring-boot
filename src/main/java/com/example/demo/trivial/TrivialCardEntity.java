package com.example.demo.trivial;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "cards")
public class TrivialCardEntity {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;

    @Column(unique = true, updatable = true, insertable = true, name = "question", columnDefinition = "TEXT")
    private String question;

    @Column(updatable = true, insertable = true, name = "right_answer", columnDefinition = "TEXT")
    private String rightAnswer;

    @Column(updatable = true, insertable = true, name = "wrong_answer_1", columnDefinition = "TEXT")
    private String wrongAnswer1;

    @Column(updatable = true, insertable = true, name = "wrong_answer_2", columnDefinition = "TEXT")
    private String wrongAnswer2;

    @Column(updatable = true, insertable = true, name = "wrong_answer_3", columnDefinition = "TEXT")
    private String wrongAnswer3;

    @Range(min = 0, max = 9)
    private int category;

    @Range(min = 0, max = 2)
    private int difficulty;
    
}
