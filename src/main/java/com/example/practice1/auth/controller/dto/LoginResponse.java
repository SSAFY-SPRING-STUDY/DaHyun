package com.example.practice1.auth.controller.dto;

// acessToken은 uuid, tokenType은 Bearer
public record LoginResponse(String accessToken, String tokenType) {

  public static LoginResponse of(String accessToken, String tokenType) {
    return new LoginResponse(accessToken, tokenType);
  }

}
