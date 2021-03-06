package study.boot.proj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import study.boot.proj.dto.Member;

import java.util.Optional;

public interface MemberRefo extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    // 회원가입
    public int RegisterInsert(Member member);
    // 회원 정보 조회
    public Member SearchMemberInfo(Member member);

}
