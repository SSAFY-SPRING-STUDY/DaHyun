package com.example.practice1.member.controller;

import com.example.practice1.member.controller.dto.MemberRequest;
import com.example.practice1.member.controller.dto.MemberResponse;
import com.example.practice1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

  private final MemberService memberService;

  // 회원가입
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MemberResponse join(@RequestBody MemberRequest memberRequest) {
    return memberService.save(memberRequest);
  }

  //내 정보조회
  @GetMapping("/me")
  @ResponseStatus(HttpStatus.OK)
  public MemberResponse me(@RequestHeader("Authorization") String token) {
    return memberService.findByUuid(token);
  }

}
