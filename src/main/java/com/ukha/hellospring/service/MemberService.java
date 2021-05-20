package com.ukha.hellospring.service;

import com.ukha.hellospring.domain.Member;
import com.ukha.hellospring.repository.MemberRepository;
import com.ukha.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        // 회원 저장
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // Refactor -> Extract Method 로직을 메소드로 뽑기
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(member1 -> { //ifPresent는 Optional이여서 쓸 수 있는것..
            //전달받은 member객체의 name이 이미 존재하는 이름이라면(result가 null이 아니라면)
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원번호로 회원정보 찾기
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
