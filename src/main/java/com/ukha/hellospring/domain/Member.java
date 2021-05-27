package com.ukha.hellospring.domain;

import javax.persistence.*;

@Entity // 이러면 jpa가 관리하는 엔티티다.
public class Member {

    @Id //Primary Key 임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id값을 AutoIncrement 하는것을 아이덴티티 전략이라고 한다
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
