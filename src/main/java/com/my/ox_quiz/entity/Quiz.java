package com.my.ox_quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Quiz extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10000, nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean answer;

    @Column(nullable = false)
    private String writer;
}
