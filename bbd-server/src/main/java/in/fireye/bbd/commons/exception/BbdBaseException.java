package in.fireye.bbd.commons.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class BbdBaseException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = -5155653929577890527L;

  private final String errorCode;
  private final String errorMessage;


  public BbdBaseException(String errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

}
