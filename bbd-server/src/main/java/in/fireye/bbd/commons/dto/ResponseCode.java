package in.fireye.bbd.commons.dto;

import lombok.Getter;

@Getter
public enum ResponseCode {
  SUCCESS("success", "成功"),
  FAIL("fail", "失败"),
  PARAM_ERROR("error.param", "参数错误"),
  SERVER_ERROR("error.server", "服务器错误"),
  TOKEN_EXPIRED("token.expired", "token过期"),
  TOKEN_INVALID("token.invalid", "token无效");

  private final String code;
  private final String message;

  ResponseCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
