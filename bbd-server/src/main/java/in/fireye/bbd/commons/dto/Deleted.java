package in.fireye.bbd.commons.dto;

import lombok.Getter;

@Getter
public enum Deleted {
  YES("1", "已删除"), NO("0", "未删除");

  private final String code;
  private final String message;

  Deleted(String code, String message) {
    this.code = code;
    this.message = message;
  }

}
