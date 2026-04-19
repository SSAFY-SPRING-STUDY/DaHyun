package com.example.practice1.auth.component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

  ConcurrentHashMap<String, Long> sessionStore = new ConcurrentHashMap<>();

  public String createSession(Long id) {
    String uuid = UUID.randomUUID().toString();
    sessionStore.put(uuid, id);
    return uuid;
  }

  public void removeSession(String token) {
    String uuid = removeTokenType(token);
    sessionStore.remove(uuid);
  }

  public Long getMemberId(String token) {
    String uuid = removeTokenType(token);
    return sessionStore.get(uuid);
  }

  private String removeTokenType(String token) {
    // 받아온 토큰 값은 "Bearer" + uuid 값이기 때문에
    // 앞에 있는 "Bearer"를 없앤 uuid값만을 전송해야한다...!!
    return token.replace("Bearer", "");
  }

}
