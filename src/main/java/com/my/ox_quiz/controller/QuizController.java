package com.my.ox_quiz.controller;

import com.my.ox_quiz.dto.MemberDto;
import com.my.ox_quiz.dto.QuizDto;
import com.my.ox_quiz.service.MemberService;
import com.my.ox_quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    private final MemberService memberService;

    @GetMapping()
    public String quizAdmin(Model model){
        model.addAttribute("quizList", quizService.findAll());
        return "quiz/list";
    }

    @PostMapping("/insert")
    public String quizInsert(@ModelAttribute("dto")QuizDto dto) {
        quizService.saveQuiz(dto);
        return "redirect:/quiz";
    }

    @GetMapping("/{id}")
    public String quizUpdateForm(@PathVariable("id")Long id,
                                 Model model){
        QuizDto dto = quizService.findById(id);
        if(dto == null) {
            return "redirect:/quiz";
        } else {
            model.addAttribute("dto", dto);
            return "quiz/update";
        }
    }

    @PostMapping("/update")
    public String quizUpadate(@ModelAttribute("dto")QuizDto dto){
        quizService.saveQuiz(dto);
        return "redirect:/quiz";
    }

    @PostMapping("/delete")
    public String quizDelete(@RequestParam("id")Long id){
        quizService.delete(id);
        return "redirect:/quiz";
    }

    
}
