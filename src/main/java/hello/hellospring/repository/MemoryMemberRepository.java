package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 세팅
        store.put(member.getId(), member); // id와 이름을 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Optional을 이용하여 null 값을 처리
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store를 반복해서 확인하면서
                .filter(member -> member.getName().equals(name)) // name이랑 같은 member가 있으면 filter되고
                .findAny(); // filter 된 게 있다. 즉 name과 같은 member를 찾았다. 그러면 바로 반환 아니면 끝까지 루프를 돌고 null을 optional 로 감싸서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
