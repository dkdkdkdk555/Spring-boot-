package com.ukha.hellospring.repository;

import com.ukha.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 시퀀스로 Member의 아이디값 set
        store.put(member.getId(), member); // 스토어에 넣어줌
        return member; // 저장한 객체리턴
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이여도 Optional로 감싸면 반환이 가능하다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // stream()???
                .filter(member -> member.getName().equals(name)) // 메소드를 호출하면서 전달한 name과 같은게 있는지 필터링
                .findAny(); //하나찾아지면 반환하는 메소드
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store.values() 는 store의 모든 Member객체들이다.
    }

    public void clearStore() {
        store.clear(); //스토어 싹 비우기
    }
}
