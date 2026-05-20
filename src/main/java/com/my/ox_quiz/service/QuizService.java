package com.my.ox_quiz.service;

import com.my.ox_quiz.dto.QuizDto;
import com.my.ox_quiz.entity.Quiz;
import com.my.ox_quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;

    public List<QuizDto> findAll() {
        return quizRepository.findAll()
                .stream()
                .map(x -> QuizDto.toDto(x))
                .toList();

    }

    public QuizDto findById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        if (quiz == null) {
            return null;
        } else {
            return QuizDto.toDto(quiz);
        }
    }

    public void saveQuiz(QuizDto dto) {
        quizRepository.save(QuizDto.toEntity(dto));
    }

    public void delete(Long id) {
        quizRepository.deleteById(id);
    }
}
