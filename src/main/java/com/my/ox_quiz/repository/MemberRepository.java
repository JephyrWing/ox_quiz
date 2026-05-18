package com.my.ox_quiz.repository;

import com.my.ox_quiz.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT * FROM member WHERE id =:keyword", nativeQuery = true)
    Optional<Member> findByMemberId(@Param("keyword") String keyword);
}
