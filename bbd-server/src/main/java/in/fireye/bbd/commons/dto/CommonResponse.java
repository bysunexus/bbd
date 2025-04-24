package in.fireye.bbd.commons.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponse<T> implements Serializable {
  /**
   * 成功状态码
   */
  private final static String SUCCESS_CODE = "SUCCESS";

  /**
   * 提示信息
   */
  private String message;

  /**
   * 返回数据
   */
  private T data;

  /**
   * 状态码
   */
  private String code;

  /**
   * 状态
   */
  private Boolean state;

  /**
   * 错误明细
   */
  private String detailMessage;


  /**
   * 成功
   *
   * @param <T> 泛型
   * @return 返回结果
   */
  public static <T> CommonResponse<T> ok() {
    return ok(null);
  }

  /**
   * 成功
   *
   * @param data 传入的对象
   * @param <T>  泛型
   * @return 返回结果
   */
  public static <T> CommonResponse<T> ok(T data) {
    CommonResponse<T> response = new CommonResponse<>();
    response.code = ResponseCode.SUCCESS.getCode();
    response.data = data;
    response.message = ResponseCode.SUCCESS.getMessage();
    response.state = true;
    return response;
  }

  /**
   * 错误
   *
   * @param code    自定义code
   * @param message 自定义返回信息
   * @param <T>     泛型
   * @return 返回信息
   */
  public static <T> CommonResponse<T> error(String code, String message) {
    return error(code, message, null);
  }

  /**
   * 错误
   *
   * @param code          自定义code
   * @param message       自定义返回信息
   * @param detailMessage 错误详情信息
   * @param <T>           泛型
   * @return 返回错误信息
   */
  public static <T> CommonResponse<T> error(String code, String message, String detailMessage) {
    CommonResponse<T> response = new CommonResponse<>();
    response.code = code;
    response.message = message;
    response.state = false;
    response.detailMessage = detailMessage;
    return response;
  }

  /**
   * 错误
   *
   * @param code ResponseCode
   * @param <T>  泛型
   * @return 返回信息
   */
  public static <T> CommonResponse<T> error(ResponseCode code) {
    return error(code, null);
  }


  /**
   * 错误
   *
   * @param code          ResponseCode
   * @param detailMessage 自定义返回信息
   * @param <T>           泛型
   * @return 返回信息
   */
  public static <T> CommonResponse<T> error(ResponseCode code, String detailMessage) {
    CommonResponse<T> response = new CommonResponse<>();
    response.code = code.getCode();
    response.message = code.getMessage();
    response.state = false;
    response.detailMessage = detailMessage;
    return response;
  }

}
