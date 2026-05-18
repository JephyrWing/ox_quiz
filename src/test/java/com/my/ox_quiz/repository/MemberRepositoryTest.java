package com.my.ox_quiz.repository;

import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.MemberStatus;
import com.my.ox_quiz.entity.RoleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Test
    @DisplayName("관리자 등록")
    void createAdmin() {
        Member member = new Member();
        member.setId("Admin");
        member.setPassword(passwordEncoder.encode("1111"));
        member.setRole(RoleType.ADMIN);
        member.setStatus(MemberStatus.APPROVED);
        member.setAnswerTrue(0);
        member.setAnswerFalse(0);
        memberRepository.save(member);
        System.out.println("관리자 생성 완료");
    }
}