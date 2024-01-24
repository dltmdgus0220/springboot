package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { // test이기 때문에 public으로 할 필요 없음

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각 메소드 끝나고 호출됨
    public void afterEach() {
        repository.clearStore();
    }

    @Test // JUnit 사용
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // optional은 get 메소드를 이용해 값을 꺼냄
//        System.out.println("result = " +(result == member));
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result); // alt+enter 를 이용하면 static import를 간편하게 할 수 있음
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift+f6을 통해 동일한 변수이름을 한번에 수정 가능
        member2.setName("spring2");
        repository.save(member2);

        Member result1 = repository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result1);

        Member result2 = repository.findByName("spring2").get();
        assertThat(member2).isEqualTo(result2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat((result.size())).isEqualTo(2);
    }
}
