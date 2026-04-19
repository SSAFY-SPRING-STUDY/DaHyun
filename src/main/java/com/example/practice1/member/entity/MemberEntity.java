package com.example.practice1.member.entity;

import com.example.practice1.member.controller.dto.MemberRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {

  @Setter
  private Long id;
  private String loginId;
  private String password;
  private String name;


  private MemberEntity(String loginId, String password, String name) {
    this.loginId = loginId;
    this.password = password;
    this.name = name;
  }


  public static MemberEntity fromRequest(MemberRequest request) {
    return new MemberEntity(request.loginId(), request.password(), request.name());
  }


}
