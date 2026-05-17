package ssafy.study.ssafystudy.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.study.ssafystudy.domain.member.controller.dto.MemberRequest;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

  private static Long AUTO_INCREMENT = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String loginId;
  private String password;
  private String name;


  private MemberEntity(String loginId, String password, String name) {
    this.id = AUTO_INCREMENT++;
    this.loginId = loginId;
    this.password = password;
    this.name = name;
  }


  public static MemberEntity create(String username, String password, String nickname) {
    return new MemberEntity(username, password, nickname);
  }

  public static MemberEntity fromRequest(MemberRequest request) {
    return new MemberEntity(request.loginId(), request.password(), request.name());
  }


}
