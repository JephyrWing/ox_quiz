package com.my.ox_quiz.controller;

import com.my.ox_quiz.dto.MemberDto;
import com.my.ox_quiz.service.MemberService;
import com.my.ox_quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final QuizService quizService;
    private final MemberService memberService;

    @GetMapping("/members")
    public String memberList(Model model) {
        List<MemberDto> dtoList = memberService.findAll();
        model.addAttribute("dtoList", dtoList);
        return "member-list";
    }

    @PostMapping("/member/password")
    public String adminPwUpdate(@ModelAttribute("dto") MemberDto dto,
                                RedirectAttributes redirectAttributes) {
        MemberDto updateDto = memberService.findByMemberId(dto.getId());
        updateDto.setPassword(dto.getPassword());
        memberService.join(updateDto);
        redirectAttributes.addFlashAttribute("message", "비밀번호가 수정되었습니다.");
        return "redirect:/admin/members";
    }

    @PostMapping("/member/approve")
    public String adminApprove(@RequestParam("id") String id) {
        memberService.adminApprove(id);
        return "redirect:/admin/members";
    }
}
