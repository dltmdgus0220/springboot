package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// springdatajpa가 자동으로 구현체를 만들어주고 빈으로 등록해줌
// 이전 강의에서 MemoryMemberRepository에서 @Repository annotation을 통해 빈으로 등록했었는데
// 빈이 중복되어 오류 발생함. 따라서 @Repository를 없애 줘야함
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JpaRepository에 기본적인 기능들이 다 들어있지만
    // findByName처럼 특정 column을 통해 찾는 것과 같은 공통 클래스로 제공할 수 없는 메소드들은
    // 직접 구현해야함.
    // 그러나 단순한 쿼리들은 인터페이스 이름을 규칙에 맞게 설정하면 알아서 다 구현해줌
    // findByName 같은 경우 fineBy 뒤에 Name을 받아서 select m from Member m where m.name = ? 이라고 생성해줌
    @Override
    Optional<Member> findByName(String name);
}
