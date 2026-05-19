package com.my.ox_quiz.controller;

import com.my.ox_quiz.service.MemberService;
import com.my.ox_quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    private final MemberService memberService;

    @GetMapping()
    public String quizAdmin(){
        return "update";
    }
}
