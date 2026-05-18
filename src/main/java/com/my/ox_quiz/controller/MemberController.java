package com.my.ox_quiz.controller;

import com.my.ox_quiz.service.MemberService;
import com.my.ox_quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final QuizService quizService;
    private final MemberService memberService;

    @GetMapping("/my-page")
    public String myPage(HttpSession session, Model model) {
        if (!ObjectUtils.isEmpty(session.getAttribute("message"))){
            model.addAttribute("message", session.getAttribute("message").toString());
            session.removeAttribute("message");
        }
        return "my-page";
    }
}
