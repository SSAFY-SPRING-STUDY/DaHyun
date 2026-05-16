package ssafy.study.ssafystudy.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ssafy.study.ssafystudy.domain.auth.util.AuthTokenUtils;
import ssafy.study.ssafystudy.domain.member.controller.dto.MemberRequest;
import ssafy.study.ssafystudy.domain.member.controller.dto.MemberResponse;
import ssafy.study.ssafystudy.domain.member.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

  private final MemberService memberService;

  // 회원가입
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MemberResponse join(@RequestBody MemberRequest memberRequest) {
    log.info("in /api/members, memberRequest={}", memberRequest);
    return memberService.save(memberRequest);
  }

  //내 정보조회
  @GetMapping("/me")
  @ResponseStatus(HttpStatus.OK)
  public MemberResponse me(@RequestHeader("Authorization") String token) {
    //"Bearer asdfjosijtlxzc"
    String sessionKey = AuthTokenUtils.pareBearerToken(token);
    return memberService.findByUuid(sessionKey);
  }

}
