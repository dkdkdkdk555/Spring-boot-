package com.ukha.hellospring.repository;

import com.ukha.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional이면 결과가 null일때 Optional로 포장해서 리턴하는데 요즘 이걸 선호한다고 한다.(자바8)
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
