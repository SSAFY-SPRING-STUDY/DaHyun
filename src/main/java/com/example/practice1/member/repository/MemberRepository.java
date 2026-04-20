package com.example.practice1.member.repository;

import com.example.practice1.member.entity.MemberEntity;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

  private static final Map<Long, MemberEntity> memberStore = new ConcurrentHashMap<>();
  private static Long AUTO_INCREMENT = 1L;

  public Optional<MemberEntity> findById(Long id) {
    return memberStore.values().stream().filter(memberEntity -> (memberEntity.getId().equals(id)))
        .findAny();
  }

  // 회원 엔티티 저장(ID 자동 생성 로직 포함)
  public MemberEntity save(MemberEntity member) {
    member.setId(AUTO_INCREMENT++); // 여기서 ++을 하고
    memberStore.put(member.getId(), member); // ++한 member.getId()를 가져온다.
    return member;
  }

  public Optional<MemberEntity> findByLoginId(String loginId) {

    return memberStore.values().
        stream().
        filter(memberEntity -> (memberEntity.getLoginId().equals(loginId))).
        findAny();
  }
}
