package com.example.practice1.member.controller.dto;

import com.example.practice1.member.entity.MemberEntity;

public record MemberResponse(Long id, String loginId, String name) {

  public static MemberResponse fromEntity(MemberEntity memberEntity) {
    return new MemberResponse(memberEntity.getId(),
        memberEntity.getLoginId(), memberEntity.getName());
  }

}
