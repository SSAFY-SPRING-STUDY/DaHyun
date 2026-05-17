package ssafy.study.ssafystudy.domain.post.entity;

import lombok.Getter;
import ssafy.study.ssafystudy.domain.member.entity.MemberEntity;

@Getter
public class PostEntity {
  // 이번에는 List<> 형태로 구현할거라 @Entity가 없다!

  private static Long AUTO_INCREMENT_ID = 1L;

  private Long id;
  private String title;
  private String content;
  private MemberEntity author;

  public PostEntity(String title, String content, MemberEntity author) {
    this.id = AUTO_INCREMENT_ID++;
    this.title = title;
    this.content = content;
    this.author = author;
  }


  public static PostEntity create(String title, String content, MemberEntity author) {
    return new PostEntity(title, content, author);
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
