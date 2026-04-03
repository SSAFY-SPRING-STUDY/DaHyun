package com.example.practice1.controller.entity;

import lombok.Getter;

@Getter
public class PostEntity {
    // 이번에는 List<> 형태로 구현할거라 @Entity가 없다!

    private static Long AUTO_INCREMENT = 1L;

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostEntity(String title, String content, String author) {
        this.id = AUTO_INCREMENT++;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
