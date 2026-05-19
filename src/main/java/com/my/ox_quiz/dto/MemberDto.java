package com.my.ox_quiz.dto;

import com.my.ox_quiz.entity.Member;
import com.my.ox_quiz.entity.MemberStatus;
import com.my.ox_quiz.entity.RoleType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private Long no;
    private String id;
    private String password;
    private RoleType role = RoleType.USER;
    private MemberStatus status = MemberStatus.PENDING;
    private Integer answerTrue = 0;
    private Integer answerFalse = 0;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Member toEntity(MemberDto dto) {
        Member member = new Member(
                dto.getNo(),
                dto.getId(),
                dto.getPassword(),
                dto.getRole(),
                dto.getStatus(),
                dto.getAnswerTrue(),
                dto.getAnswerFalse());
        return member;
    }

    public static MemberDto toDto(Member member) {
        MemberDto dto = new MemberDto(
                member.getNo(),
                member.getId(),
                member.getPassword(),
                member.getRole(),
                member.getStatus(),
                member.getAnswerTrue(),
                member.getAnswerFalse(),
                member.getCreatedAt(),
                member.getUpdatedAt());
        return dto;
    }
}
