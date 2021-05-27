package com.ukha.hellospring.service;

import com.ukha.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.swing.*;
import java.lang.reflect.Member;
/*
    스프링 빈을 등록하는 방법 2가지
    -1. 직접 어노테이션 달기
    -2. SpringConfig 파일 만들어서 해당객체를 리턴하는 메소드에 @Bean 해줘서 그 객체(클래스)를 빈에 등록하기
 */

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    private DataSource dataSource;
//
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
//        //return new JpaMemberRepository(em);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JdbcMemberRepository(dataSource);
//
//        /*
//            MemoryMemberRepository나 JdbcMemberRepository나 둘다 MemberRepository 인페 구현객체기 때문에
//         이 Bean등록을 새로하거나 할 필요없이 리턴문만 수정해주면된다. (리턴하는 구현객체만 바꾸면된다.)
//         -->  이러면 DBMS가 정해지지 않은 상태에서 개발에 착수했을 때 메모리에다 테스트하다가 추후
//         db가 선정되고 나서 손쉽게 옮길(?) 수 있다.
//        */
//   }
}
