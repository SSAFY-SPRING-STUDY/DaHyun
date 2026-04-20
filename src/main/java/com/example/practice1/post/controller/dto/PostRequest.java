package com.example.practice1.post.controller.dto;

public record PostRequest(String title, String content, String author) {

  @Override
  public String toString() {
    return "PostRequest{" +
        "title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", author='" + author + '\'' +
        '}';
  }

  // record일 때에는
  // title(), content(), author() 가 getter 메서드임!!
  //
}
