package com.my.ox_quiz.dto;

import com.my.ox_quiz.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Long id;
    private String content;
    private Boolean answer;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Quiz toEntity(QuizDto dto) {
        Quiz quiz = new Quiz(dto.getId(),
                dto.getContent(),
                dto.getAnswer(),
                dto.getWriter());
        return quiz;
    }

    public static QuizDto toDto(Quiz quiz) {
        QuizDto dto = new QuizDto(quiz.getId(),
                quiz.getContent(),
                quiz.getAnswer(),
                quiz.getWriter(),
                quiz.getCreatedAt(),
                quiz.getUpdatedAt());
        return dto;
    }
}
