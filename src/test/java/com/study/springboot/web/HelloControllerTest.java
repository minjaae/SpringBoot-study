package com.study.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class) //테스트를 진행할 떄 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴. 여기서는 SpringRunner라는 스프링 실행자를 사용함. 즉 스프링 부트 테스트와 JUnit사이에 연결자 역할을 함.
@WebMvcTest(controllers = HelloController.class) //여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션. 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음. 단, @Service, @Component, @Repository 등은 사용할 수 없음. 여기서는 컨트롤러만 사용하기 떄문에 선언.
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈을 주입 받음.
    private MockMvc mvc; //웹 API를 테스트 할 때 사용. 스프링 MVC 테스트의 시작점. 이 클래스를 통해 HTTP GET,POST 등에 대한 API 테스트를 할 수 있음.

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello ="hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET 요청. 체이닝이 지원되어 여러 검정 기능 이어서 선언 가능.
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증. HTTP Header의 Status검증. 우리가 흔히 알고 있는 200, 404, 500등의 상태 검증. 여기선 OK 즉, 200인지 아닌지 검증.
                .andExpect(content().string(hello)); //mvc.perform의 결과 검증. 응답 본문의 내용 검증. Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증.
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name ="hello";
        int amount =1000;

        mvc.perform(get("/hello/dto")
                .param("name",name) //API테스트할 떄 사용될 요청 파라미터를 설정. 단, 값은 String만 허용. 그래서 숫자/날짜 등의 데이터 등록시 문자열로 변경해야만 가능.
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name))) //JSON응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명 명시.
                .andExpect(jsonPath("$.amount",is(amount))); //name과 amount를 검증하니 $name, $amount로 검증.
    }
}
