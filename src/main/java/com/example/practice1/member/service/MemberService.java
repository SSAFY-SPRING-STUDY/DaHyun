package com.example.practice1.member.service;

import com.example.practice1.auth.component.SessionManager;
import com.example.practice1.member.controller.dto.MemberRequest;
import com.example.practice1.member.controller.dto.MemberResponse;
import com.example.practice1.member.entity.MemberEntity;
import com.example.practice1.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;
  private final SessionManager sessionManager;

  public MemberResponse save(MemberRequest request) {
    MemberEntity memberEntity = MemberEntity.fromRequest(request);
    return MemberResponse.fromEntity(memberRepository.save(memberEntity));
  }

  public MemberResponse findByUuid(String token) {
    Long id = sessionManager.getMemberId(token);
    Optional<MemberEntity> entity = memberRepository.findById(id);
    return MemberResponse.fromEntity(entity.get());
  }
}
