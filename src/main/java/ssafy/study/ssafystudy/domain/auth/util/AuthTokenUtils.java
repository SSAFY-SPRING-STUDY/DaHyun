package ssafy.study.ssafystudy.domain.auth.util;

import ssafy.study.ssafystudy.global.exception.CustomException;
import ssafy.study.ssafystudy.global.exception.error.ErrorCode;

public class AuthTokenUtils {

  private static final String BEARER_PREFIX = "Bearer ";

  private AuthTokenUtils() {

  }

  public static void isValidBearerToken(String bearerToken) {
    if (bearerToken == null || !bearerToken.startsWith(BEARER_PREFIX)) {
      throw new CustomException(ErrorCode.INVALID_TOKEN);
    }

    String sessionKey = bearerToken.substring(BEARER_PREFIX.length());

    if (sessionKey.isBlank()) {
      throw new CustomException(ErrorCode.INVALID_TOKEN);
    }

  }

  public static String pareBearerToken(String bearerToken) {
    isValidBearerToken(bearerToken);

    return bearerToken.substring(BEARER_PREFIX.length());
  }

}
