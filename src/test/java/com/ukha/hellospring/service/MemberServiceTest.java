package com.ukha.hellospring.service;

import com.ukha.hellospring.domain.Member;
import com.ukha.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach //각 테스트를 실행하기 전에 실행하게 하는 어노
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // 이렇게 하면 결국 전체에 생성되는 리포지토리는 하나다.
    }

    @AfterEach //메모리 클리어 처리
    public void afterEach() {
        memberRepository.clearStore();
    }

    //테스트 코드는 빌드될때 나가지 않기 때문에 메소드이름을 과감히 한글로 해도 된다.
    @Test
    void 회원가입() {
        //given 이게 주어졌을 때
        Member member = new Member();
        member.setName("spring");

        //when 이걸 실행했을 때
        Long savedId = memberService.join(member);

        //then 결과가 이게 나와야한다
        Member findMember = memberService.findOne(savedId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName()); // 결과 확인
    }

    @Test
    public void 중복회원예외테스트() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 발생한 예외가 같아야 성공

//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}