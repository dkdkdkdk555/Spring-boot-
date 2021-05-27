package com.ukha.hellospring.service;

import com.ukha.hellospring.domain.Member;
import com.ukha.hellospring.repository.MemberRepository;
import com.ukha.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 이걸 해주면 테스트를 돌리기전에 트랜젝셔널이 먼저 실행되고 테스트가 끝나면 넣었던 데이터를 자동 삭제해준다
    // 그래서 @AfterEach, @BeforeEach 처리를 할 필요가 없는거다.
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    //테스트할때는 그냥 필드에다 autowired해줘도 된다.

    @Test
    void 회원가입() {
        //given 이게 주어졌을 때
        Member member = new Member();
        member.setName("오토다케");

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
        member1.setName("spring@#@#");

        Member member2 = new Member();
        member2.setName("spring@#@#");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 발생한 예외가 같아야 성공

    }

}