package in.fireye.bbd.commons;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
  public static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");

  public static String getTodayString(@NotNull DateTimeFormatter formatter) {
    return LocalDateTime.now().format(formatter);
  }

  public static LocalDateTime getToday() {
    return LocalDateTime.now();
  }

  public static LocalDateTime dayPlus(LocalDateTime dateTime, int day) {
    return dateTime.plusDays(day);
  }


  public static LocalDateTime minusDays(LocalDateTime dateTime, int day) {
    return dateTime.minusDays(day);
  }

  /**
   * 解析日期字符串
   *
   * @param dateString 日期字符串，***不能包含时间
   * @param formatter  日期格式
   * @return 日期时间对象
   */
  public static LocalDateTime parse(@NotNull String dateString, @NotNull DateTimeFormatter formatter) {
    return formatter.parse(dateString, LocalDate::from).atStartOfDay();
  }

  /**
   * 解析日期字符串
   *
   * @param dateString 日期字符串，***不能包含时间
   * @param formatter  日期格式
   * @return 日期时间对象
   */
  public static LocalDateTime parseAtStartOfDay(@NotNull String dateString, @NotNull DateTimeFormatter formatter) {
    return formatter.parse(dateString, LocalDate::from).atStartOfDay();
  }

  /**
   * 解析日期字符串
   *
   * @param dateString 日期字符串，***不能包含时间
   * @param formatter  日期格式
   * @return 日期时间对象
   */
  public static LocalDateTime parseAtEndOfDay(@NotNull String dateString, @NotNull DateTimeFormatter formatter) {
    return formatter.parse(dateString, LocalDate::from).plusDays(1).atStartOfDay();
  }

  public static String format(@NotNull LocalDateTime dateTime, @NotNull DateTimeFormatter formatter) {
    return formatter.format(dateTime);
  }

  public static void main(String[] args) {
    System.out.println(DateUtils.parse("20250502", YYYYMMDD));
  }

}
