package in.fireye.bbd.commons.dto;

import lombok.Getter;

@Getter
public enum InOutSign {
  IN("I", "收入"), OUT("O", "支出");

  private final String code;
  private final String message;

  InOutSign(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
