package ssafy.study.ssafystudy.domain.post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.study.ssafystudy.domain.member.entity.MemberEntity;

@Getter
@Entity
@NoArgsConstructor
public class PostEntity {
  // 이번에는 List<> 형태로 구현할거라 @Entity가 없다!

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id")
  private MemberEntity author;

  public PostEntity(String title, String content, MemberEntity author) {
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
