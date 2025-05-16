package in.fireye.bbd.server.ai.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum DirectResultDataType {
  text("text"),
  ledger("ledger"),
  stat("stat"),
  tip("tip");

  @JsonValue
  private final String dataType;

  DirectResultDataType(String dataType) {
    this.dataType = dataType;
  }

}
