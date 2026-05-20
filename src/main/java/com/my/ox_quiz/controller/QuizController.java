package com.my.ox_quiz.controller;

import com.my.ox_quiz.dto.MemberDto;
import com.my.ox_quiz.dto.QuizDto;
import com.my.ox_quiz.service.MemberService;
import com.my.ox_quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    private final MemberService memberService;

    @GetMapping()
    public String quizAdmin(Model model) {
        model.addAttribute("quizList", quizService.findAll());
        return "quiz/list";
    }

    @PostMapping("/insert")
    public String quizInsert(@ModelAttribute("dto") QuizDto dto) {
        quizService.saveQuiz(dto);
        return "redirect:/quiz";
    }

    @GetMapping("/{id}")
    public String quizUpdateForm(@PathVariable("id") Long id,
                                 Model model) {
        QuizDto dto = quizService.findById(id);
        if (dto == null) {
            return "redirect:/quiz";
        } else {
            model.addAttribute("dto", dto);
            return "quiz/update";
        }
    }

    @PostMapping("/update")
    public String quizUpadate(@ModelAttribute("dto") QuizDto dto) {
        quizService.saveQuiz(dto);
        return "redirect:/quiz";
    }

    @PostMapping("/delete")
    public String quizDelete(@RequestParam("id") Long id) {
        quizService.delete(id);
        return "redirect:/quiz";
    }

    @GetMapping("/play")
    public String quizPlay(Model model) {
        QuizDto dto = quizService.playQuiz();
        if (dto == null) {
            model.addAttribute("message", "아직 등록된 문제가 없습니다.");
            return "quiz/play";
        } else {
            model.addAttribute("dto", dto);
            return "quiz/play";
        }
    }

    @PostMapping("/check")
    public String quizCheck(@RequestParam("id")Long id,
                            @RequestParam("userAnswer")Boolean ans,
                            Model model,
                            HttpSession session) {
        QuizDto dto = quizService.findById(id);
        Boolean result;
        MemberDto member = (MemberDto) session.getAttribute("loginMember");
        if (dto.getAnswer() == ans) {
            result = TRUE;
            member.setAnswerTrue(member.getAnswerTrue()+1);
        } else {
            result = FALSE;
            member.setAnswerFalse(member.getAnswerFalse()+1);
        }
        memberService.update(member);
        model.addAttribute("result", result);
        return "quiz/result";
    }

}
