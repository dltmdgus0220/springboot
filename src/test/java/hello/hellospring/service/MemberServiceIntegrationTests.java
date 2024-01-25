package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

// 기존 테스트와 달리 현재 db 데이터를 springboot에서 관리하고 있기 때문에
// 이에 접근해서 테스트하기 위해서는 아래와 같은 annotation을 추가해야함
@SpringBootTest
@Transactional // transaction을 통해 테스트가 끝나면 테스트 하기 전으로 롤백해줌
class MemberServiceIntegrationTests {

    // 원래 파일은 생성자를 이용한 DI를 사용하는 것이 일반적이지만
    // 테스트 할 때는 테스트 시에만 사용하고 다른 파일에서 가져다 사용하는 것이 아니기 때문에
    // 편하게 바로 필드 DI 하는 경우도 있다
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");
        // when
        Long saveId = memberService.join(member);
        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 람다 로직을 수행을 하면 illegalstateexception이 수행되어야 함. 수행 안되면 실패했다고 뜸
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}