package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService { // ctrl+shift+t 를 통해 테스트 세팅 가능

    private final MemberRepository memberRepository;
    // constructor를 사용, 각 MemberService 객체마다 각각의 memberRepository를 가질 수 있게 됨
    // Dependency Injection => DI
    @Autowired // MemberRepository와 연결하기 위함
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
        validateDuplicateMember(member); // 드래그 후 extract method로 빼낼 수 있음
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // optional 메소드 중 하나. null이 아닌 값을 가지면 아래 동작
                    // 비슷하게 orElseGet 이라는 메소드를 사용하여 값의 유무에 따라 동작 달리 할 수 있음
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
