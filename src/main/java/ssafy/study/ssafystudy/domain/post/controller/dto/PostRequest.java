package ssafy.study.ssafystudy.domain.post.controller.dto;

import ssafy.study.ssafystudy.domain.member.entity.MemberEntity;
import ssafy.study.ssafystudy.domain.post.entity.PostEntity;

public record PostRequest(String title, String content) {

  // record일 때에는 title(), content()가 getter 메서드임
// author는 요청에서 받지 않고 세션에서 가져와 toEntity(author)로 전달함

  public PostEntity fromEntity(MemberEntity author) {
    return PostEntity.create(title, content, author);
  }


}
