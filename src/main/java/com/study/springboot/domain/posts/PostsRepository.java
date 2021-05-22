package com.study.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//보통 MyBatis 등에서 Dao라고 불리는 DBLayer 접근자. JPA에서는 Repository라고 부르며 인터페이스로 생성.
public interface PostsRepository extends JpaRepository<Posts,Long> { //단순히 인터페이스 생성후, JpaRepository<Entity클래스,PK타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성됨.
//Entity클래스와 기본 Entity Repository는 함께 위치해야함.

}
