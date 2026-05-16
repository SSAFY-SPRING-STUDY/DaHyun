package ssafy.study.ssafystudy.domain.post.controller.dto;

import ssafy.study.ssafystudy.domain.member.controller.dto.MemberResponse;
import ssafy.study.ssafystudy.domain.post.entity.PostEntity;

public record PostResponse(Long id, String title, String content, MemberResponse memberResponse) {

  public static PostResponse fromEntity(PostEntity postEntity) {
    return new PostResponse(postEntity.getId(), postEntity.getTitle(), postEntity.getContent(),
        MemberResponse.fromEntity(postEntity.getAuthor()));
  }
}
