package in.fireye.bbd.commons.exception;

import in.fireye.bbd.commons.dto.ResponseCode;

import java.io.Serial;

public class BbdBusinessException extends BbdBaseException {


  @Serial
  private static final long serialVersionUID = 2778071505004756712L;

  public BbdBusinessException(ResponseCode code) {
    super(code.getCode(), code.getMessage());
  }
}
