package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;
    // MemberService는 단순한 java class이기 때문에 spring이 찾을 수 없다.
    // 때문에 MemberService에 @Service를 적어줘야 spring이 찾아서 MemberController와 연결시켜준다.
    // MemoryMemberRepository도 마찬가지로 @Repository를 적어줘야한다.
    @Autowired // MemberService와 연결하기 위함
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
