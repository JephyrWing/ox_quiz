package com.my.ox_quiz.service;

import com.my.ox_quiz.dto.MemberDto;
import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.MemberStatus;
import com.my.ox_quiz.entity.RoleType;
import com.my.ox_quiz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<MemberDto> findAll() {
        return memberRepository.findAll()
                .stream()
                .map(x->MemberDto.toDto(x))
                .toList();
    }

    public MemberDto findByMemberId(String id) {
        Member member = memberRepository.findByMemberId(id).orElse(null);
        if (member == null){
            return null;
        } else {
            return MemberDto.toDto(member);
        }
    }

    public void join(MemberDto dto) {
        Member member = new Member();
        member.setNo(dto.getNo());
        member.setId(dto.getId());
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setRole(dto.getRole());
        member.setStatus(dto.getStatus());
        member.setAnswerTrue(dto.getAnswerTrue());
        member.setAnswerFalse(dto.getAnswerFalse());
        memberRepository.save(member);
    }

    public MemberDto login(MemberDto dto) {
        MemberDto loginDto = findByMemberId(dto.getId());
        if (loginDto == null) return null;
        boolean matches = passwordEncoder.matches(dto.getPassword(), loginDto.getPassword());
        if (matches) return loginDto;
        else return null;
    }

}
