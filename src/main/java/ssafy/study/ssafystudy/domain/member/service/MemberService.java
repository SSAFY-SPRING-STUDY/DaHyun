package ssafy.study.ssafystudy.domain.member.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.study.ssafystudy.domain.auth.component.SessionManager;
import ssafy.study.ssafystudy.domain.member.controller.dto.MemberRequest;
import ssafy.study.ssafystudy.domain.member.controller.dto.MemberResponse;
import ssafy.study.ssafystudy.domain.member.entity.MemberEntity;
import ssafy.study.ssafystudy.domain.member.repository.MemberRepository;
import ssafy.study.ssafystudy.global.exception.CustomException;
import ssafy.study.ssafystudy.global.exception.error.ErrorCode;

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
    Optional<MemberEntity> entity = Optional.ofNullable(memberRepository.findById(id)
        .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)));
    return MemberResponse.fromEntity(entity.get());
  }
}
