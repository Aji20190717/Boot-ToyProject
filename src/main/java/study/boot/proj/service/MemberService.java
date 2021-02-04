package study.boot.proj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.boot.proj.dao.MemberRefo;
import study.boot.proj.dto.Member;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService, Register {

    private final MemberRefo userRepository;

    /**
     * Spring Security 필수 메소드 구현
     *
     * @param email 이메일
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */
    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public Member loadUserByUsername(String email) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException((email)));
    }

    // 회원가입
    @Override
    public int InsertMemberInfo(Member member) {
        return 0;
    }

    // 회원 정보 조회
    @Override
    public Member SearchMemberInfo(Member member) {
        return null;
    }

    // 회원 정보 수정
    @Override
    public int UpdateMemberInfo(Member member) {
        return 0;
    }

    // 회원 탈퇴 -> update
    @Override
    public int DeleteMemberInfo(int seq) {
        return 0;
    }

    // email 중복 확인
    @Override
    public String SearchEmail(String email) {
        return null;
    }

    // 탈퇴 회원 여부 확인
    @Override
    public String SearchWithdrawal(int seq) {
        return null;
    }

}
