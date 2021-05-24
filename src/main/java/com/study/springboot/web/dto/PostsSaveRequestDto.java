package com.study.springboot.web.dto;

import com.study.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
        public Posts toEntity(){
            return Posts.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .build();
    }
}

//Entity 클래스와 거의 유사. 절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안됨.
//Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스. Entity클래스를 기준으로 테이블이 생성되고, 스키마가 변경됨.
//View Layer와 DB Layer의 역할 분리를 철저하게 하는게 좋음.

