package com.ukha.hellospring.repository;

import com.ukha.hellospring.domain.Member;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;// jpa는 이걸로 동작한다. jpa 받으면 이걸 알아서 생성해준다. 그럼 이걸 인젝션 받으면됨

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member as m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class).getResultList();
        //테이블 대상이 아닌 객체를 대상으로 쿼리를 날리는거다(그럼 sql로 번역이 됨)
        // 여기선 Member 엔티티에 조회하라고 하는거 ( 별칭 m 객체 자체를 셀렉트함.. 이해안된다.) --> 이런 쿼리를 Jpql이라함
    }
}
