package com.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//프로젝트의 메인 클래스
@SpringBootApplication //스프링부트의 자동설정,스프링 Bean 읽기와 생성을 모두 자동으로 설정, 이 위치부터 설정을 읽어가기 떄문에 이 클래스는 항상 프로젝트의 최상단에 위치 해야함.
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args); //내장 WAS (Web Application Server) 실행.
    }
}
