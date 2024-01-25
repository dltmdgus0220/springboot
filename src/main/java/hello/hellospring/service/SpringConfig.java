package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// annotation을 이용한 component scan으로 스프링빈 등록하는 것이 아닌
// 직접 java 코드로 스프링빈 등록
@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository) {
        this.dataSource = dataSource;
        this.em = em;
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource); // 순수 jdbc 구현체
////        return new JdbcTemplateMemberRepository(dataSource); // jdbc template
//        return new JpaMemberRepository(em); // jpa
//    }


}
