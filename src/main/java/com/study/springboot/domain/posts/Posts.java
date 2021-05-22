package com.study.springboot.domain.posts; //도메인이란 게시글,댓글, 회원, 정산, 결제 등 소프트웨어에 대한 요구사항 혹은 문제영역.

//import javax.persistence.*;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

//어노테이션 순서 : 주요 어노테이션을 클래스에 가깝게
@Getter //롬복 어노테이션 (필수 어노테이션은 아님), 클래스 내 모든 필드의 Getter 메소드를 자동생성.
@NoArgsConstructor //롬복 어노테이션, 기본생성자 자동추가. public Posts(){}와 같은 효과.
@Entity //JPA 어노테이션, 테이블과 링크될 클래스임을 나타냄. 기본값으로 클래스의 카멜케이스  이름을 언더스코밍 네이밍으로 테이블 이름을 매칭함.
public class Posts { //실제 DB의 테이블과 매칭될 클래스. 보통 Entity 클래스 라고함.
    @Id //JPA 어노테이션. 해당 테이블의 PK 필드를 나타냄.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //JPA 어노테이션. PK의 생성규칙을 나타냄. 스트링부트 20에서는 Generation Type.IDENTITY 옵션을 추가해야한 auto_increment가 됨.
    //웬만하면 Entity의 PK는 Long타입의 Auto_increment를 추천.(자세한 이유 p91)
    private Long id;

    @Column(length = 500, nullable = false) //JPA 어노테이션. 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨. 사용하는 이유는 기본값외에 추가로 변경이 필요햔 옵션이 있으면 사용.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) //문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나, 타입을 TEXT로 변경하고 싶거나 등의 경우에 사용.
    private String content;

    private String author;

    @Builder //롬복 라이브러리의 어노테이션. 해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
//sette를 무작정 생성하면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할수가 없어 차후 기능 변경시 목잡해짐.
//그래서 Entity 클래스에서는 절대 Setter 메소드를 만들지 않음. 대신 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야함.
//Setter 없이 어떻게 값을 채워 DB에 삽입? 기본적인 구조는 생성자를 통해 최종값을 채운후 DB에 삽입. 값 변경이 필요할 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경하는 것을 전제로 함.
//여기서는 생성자 대신 @Builder를 통해 제공되는 빌더 클래스를 사용. 빌더를 사용하면 어느 필드에 어떤 값을 채워야 할지 명확하게 인지할 수 있음.
//Example.builder().a(a).b(b).build();