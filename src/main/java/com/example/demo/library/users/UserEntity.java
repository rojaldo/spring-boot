package com.example.demo.library.users;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.example.demo.library.lends.LendEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long id;

    @Length(min = 3, max = 50)
    private String name;

    @Column(unique = true, nullable = false, length = 80, updatable = true, insertable = true, name = "email")
    private String email;

    @Range(min = 1, max = 150)
    private int age;

    @OneToMany(mappedBy = "user")
    private List<LendEntity> lends;
}
