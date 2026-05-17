package ssafy.study.ssafystudy.domain.auth.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.study.ssafystudy.domain.auth.component.SessionManager;
import ssafy.study.ssafystudy.domain.auth.controller.dto.LoginRequest;
import ssafy.study.ssafystudy.domain.auth.controller.dto.LoginResponse;
import ssafy.study.ssafystudy.domain.member.entity.MemberEntity;
import ssafy.study.ssafystudy.domain.member.repository.MemberRepository;
import ssafy.study.ssafystudy.global.exception.CustomException;
import ssafy.study.ssafystudy.global.exception.error.ErrorCode;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final SessionManager sessionManager;
  private final MemberRepository memberRepository;


  public LoginResponse login(LoginRequest request) {
    Optional<MemberEntity> entity = memberRepository.findByLoginId(request.loginId());

    if (entity.isEmpty()) {
      throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
    }

    if (!entity.get().getPassword().equals(request.password())) {
      throw new CustomException(ErrorCode.INVALID_PARAMETER);
    }

    String sessionKey = sessionManager.createSession(entity.get().getId());

    return LoginResponse.of(sessionKey, "Bearer");
  }


  public void logout(String token) {
    sessionManager.removeSession(token);
  }
}
