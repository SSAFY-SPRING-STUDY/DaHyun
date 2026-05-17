package ssafy.study.ssafystudy.domain.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssafy.study.ssafystudy.domain.member.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

  public MemberEntity save(MemberEntity memberEntity);

  public Optional<MemberEntity> findById(Long memberId);

  public Optional<MemberEntity> findByLoginId(String loginId);

}
