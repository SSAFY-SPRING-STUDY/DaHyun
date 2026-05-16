package ssafy.study.ssafystudy.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ssafy.study.ssafystudy.domain.auth.controller.dto.LoginRequest;
import ssafy.study.ssafystudy.domain.auth.controller.dto.LoginResponse;
import ssafy.study.ssafystudy.domain.auth.service.AuthService;
import ssafy.study.ssafystudy.domain.auth.util.AuthTokenUtils;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public LoginResponse login(@RequestBody LoginRequest request) {
    return authService.login(request);
  }

  @PostMapping("/logout")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void logout(@RequestHeader("Authorization") String token) {
    String sessionKey = AuthTokenUtils.pareBearerToken(token);
    authService.logout(sessionKey);
  }


}
