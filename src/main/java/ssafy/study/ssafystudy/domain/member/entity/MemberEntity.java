package ssafy.study.ssafystudy.domain.member.entity;

import ssafy.study.ssafystudy.domain.member.controller.dto.MemberRequest;
import lombok.Getter;

@Getter
public class MemberEntity {

  private static Long AUTO_INCREMENT = 1L;

  private final Long id;
  private final String loginId;
  private String password;
  private String name;


  private MemberEntity(String loginId, String password, String name) {
    this.id = AUTO_INCREMENT++;
    this.loginId = loginId;
    this.password = password;
    this.name = name;
  }


  public static MemberEntity fromRequest(MemberRequest request) {
    return new MemberEntity(request.loginId(), request.password(), request.name());
  }


}
