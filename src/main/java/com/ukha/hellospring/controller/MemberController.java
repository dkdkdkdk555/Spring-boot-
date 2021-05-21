package com.ukha.hellospring.controller;

import com.ukha.hellospring.domain.Member;
import com.ukha.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //Controller 어노테이션이 있으면, 스프링 컨테이너에 MemberController객체(빈)를 생성을 해서 컨테이너에 넣고 스프링이 관리한다.


    private final MemberService memberService;

    @Autowired // 스프링 빈 컨테이너에 있는 MemberService객체를 DI(의존성 주입) 해준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        
        memberService.join(member);
        
        return "redirect:/"; // 회원가입 끝냈으니 홈 화면으로 돌려보내기
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
