package hello.hellospring.domain;

import jakarta.persistence.*;

// jpa를 사용하기 위해서는 엔티티 맵핑을 해야함
@Entity
public class Member {
    // member를 테이블에 insert할 때 name은 지정해주지만 id는 자동으로 넣어주는데
    // 이를 identity라고 함. 이 identity를 위해 generatedvalue를 사용
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블의 컬럼 이름에 맞게 맵핑
    @Column(name="name")
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
