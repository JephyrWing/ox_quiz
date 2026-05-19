package com.my.ox_quiz.controller;

import com.my.ox_quiz.dto.MemberDto;
import com.my.ox_quiz.service.MemberService;
import com.my.ox_quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final QuizService quizService;
    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String join(MemberDto dto,
                       RedirectAttributes redirectAttributes,
                       HttpSession session){
        MemberDto resultDto = memberService.findByMemberId(dto.getId());
        if (resultDto != null) {
            redirectAttributes.addFlashAttribute("message", "중복된 아이디가 존재합니다.");
            return "redirect:/member/join";
        }
        memberService.join(dto);
        session.setAttribute("loginMember", dto);
        session.setMaxInactiveInterval(60*30);
        return "redirect:/member/my-page"; // 회원가입과 동시에 로그인
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(MemberDto dto,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {
        MemberDto loginDto = memberService.login(dto);
        if (loginDto == null) {
            redirectAttributes.addFlashAttribute("message", "아이디 또는 비밀번호가 틀렸습니다.");
            return "redirect:/member/login";
        } else if (loginDto.getRole().name().equals("ADMIN")) {
            session.setAttribute("loginMember", loginDto);
            session.setMaxInactiveInterval(60*30);
            return "redirect:/quiz";
        } else {
            session.setAttribute("loginMember", loginDto);
            session.setMaxInactiveInterval(60*30);
            return "redirect:/member/my-page";
        }
    }

    @GetMapping("/my-page")
    public String myPage() {
        return "my-page";
    }
    
    @PostMapping("/password")
    public String pwUpdate(@RequestParam("password") String password,
                           HttpSession session,
                           RedirectAttributes redirectAttributes){
        MemberDto dto = (MemberDto) session.getAttribute("loginMember");
        dto.setPassword(password);
        memberService.join(dto);
        redirectAttributes.addFlashAttribute("message", "비밀번호가 수정되었습니다.");
        return "redirect:/member/my-page";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
