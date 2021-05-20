package com.ukha.hellospring.repository;

import com.ukha.hellospring.domain.Member;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //Test 메소드들이 끝날 때마다 호출되는 함수 --> 왜냐면 전체 테스트할때 메소드들의 순서가 보장되지 않기 때문에 정리해줘야 이전 테스트가 영향 안준다.
    public void afterEach() {
        repository.clearStore();
        // 테스트는 서로 의존관계가 없도록 설계해야 하고
        // 항상 테스트 하나 할 때마다 저장소나 공용 데이터들을 깔끔하게 지워줘야한다.
        /*
            @AfterEach : 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다. 이렇게
           되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다. @AfterEach 를 사용하면 각 테스트가
           종료될 때 마다 이 기능을 실행한다. 여기서는 메모리 DB에 저장된 데이터를 삭제한다.
        */
    }

    @Test
    public void save() { // save기능 테스트 하기
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // 반환타입이 Optional이니까 get()으로 꺼낸다.
        //즉, get()은 Optional에 포장된 제네릭 객체를 가져오는 메소드(옵셔널 포장을 까는 메소드)

        //DB(여기선 repository)와 내가 저장한게 같으면 참.
        System.out.println(result == member); // result가 Member객체니까 .. store에 저장된게 같은 객체라는 거다.

        // Jupiter에서 제공하는 Assertions로 확인가능하다.
        Assertions.assertEquals(member, result); // 다르면 오류남

        // assertj 의 Assertions로도 확인 가능하다
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result); // 역시 다르면 오류남
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Optional<Member> op = repository.findByName("spring1");
        Member result1 = op.get();

        org.assertj.core.api.Assertions.assertThat(result1).isEqualTo(member1);

        Member result2 = repository.findByName("spring2").get();

        Assertions.assertEquals(member2, result2);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);

    }
}
