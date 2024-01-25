package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
//        System.out.println("member = " + member.getName());
        memberService.join(member);

        return "redirect:/"; // 회원가입이 끝나면 home화면으로 보냄
    }
    
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
