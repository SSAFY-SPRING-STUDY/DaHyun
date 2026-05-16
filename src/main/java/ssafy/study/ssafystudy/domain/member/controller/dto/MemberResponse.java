package ssafy.study.ssafystudy.domain.member.controller.dto;

import ssafy.study.ssafystudy.domain.member.entity.MemberEntity;

public record MemberResponse(Long id, String loginId, String name) {

  public static MemberResponse fromEntity(MemberEntity memberEntity) {
    return new MemberResponse(memberEntity.getId(),
        memberEntity.getLoginId(), memberEntity.getName());
  }

}
