package com.example.practice1.auth.service;

import com.example.practice1.auth.component.SessionManager;
import com.example.practice1.auth.controller.dto.LoginRequest;
import com.example.practice1.auth.controller.dto.LoginResponse;
import com.example.practice1.member.entity.MemberEntity;
import com.example.practice1.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final SessionManager sessionManager;
  private final MemberRepository memberRepository;


  public LoginResponse login(LoginRequest request) {
    Optional<MemberEntity> entity = memberRepository.findByLoginId(request.loginId());

    if (entity.isEmpty()) {
      throw new IllegalArgumentException("일치하는 회원ID가 없습니다.");
    }

    if (!entity.get().getPassword().equals(request.password())) {
      throw new IllegalArgumentException("비밀번호가 불일치합니다.");
    }

    String uuid = sessionManager.createSession(entity.get().getId());

    return LoginResponse.of(uuid, "Bearer");
  }


  public void logout(String token) {
    sessionManager.removeSession(uuid);
  }
}
