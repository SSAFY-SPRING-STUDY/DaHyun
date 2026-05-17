package ssafy.study.ssafystudy.domain.auth.component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ssafy.study.ssafystudy.global.exception.CustomException;
import ssafy.study.ssafystudy.global.exception.error.ErrorCode;

@Slf4j
@Component
public class SessionManager {

  ConcurrentHashMap<String, Long> sessionStore = new ConcurrentHashMap<>();

  public String createSession(Long memberId) {
    String sessionKey = UUID.randomUUID().toString();

    sessionStore.put(sessionKey, memberId);
    log.info("createSession: sessionKey={}, memberId={}", sessionKey, memberId);
    return sessionKey;
  }

  public void removeSession(String sessionKey) {
    sessionStore.remove(sessionKey);
  }

  public Long getMemberId(String sessionKey) {
    log.info("getMemberId, sessionKey={}", sessionKey);
    Long memberId = sessionStore.get(sessionKey);

    if (memberId == null) {
      throw new CustomException(ErrorCode.INVALID_TOKEN);
    }
    return memberId;
  }

  private String removeTokenType(String token) {
    // 받아온 토큰 값은 "Bearer" + uuid 값이기 때문에
    // 앞에 있는 "Bearer"를 없앤 uuid값만을 전송해야한다...!!
    return token.replace("Bearer", "");
  }

}
