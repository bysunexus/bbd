package in.fireye.bbd.commons;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateUtils {
  public static final DateTimeFormatter YYYYMMDD = DateTimeFormatter.ofPattern("yyyyMMdd");

  public static String getTodayString(@NotNull DateTimeFormatter formatter) {
    return LocalDate.now().format(formatter);
  }

  public static LocalDate getToday() {
    return LocalDate.now();
  }

  public static LocalDate dayPlus(LocalDate dateTime, int day) {
    return dateTime.plusDays(day);
  }


  public static LocalDate minusDays(LocalDate dateTime, int day) {
    return dateTime.minusDays(day);
  }

  /**
   * 解析日期字符串
   *
   * @param dateString 日期字符串，***不能包含时间
   * @param formatter  日期格式
   * @return 日期时间对象
   */
  public static LocalDate parse(@NotNull String dateString, @NotNull DateTimeFormatter formatter) {
    return formatter.parse(dateString, LocalDate::from);
  }

  public static String format(@NotNull TemporalAccessor temporal, @NotNull DateTimeFormatter formatter) {
    return formatter.format(temporal);
  }

  public static void main(String[] args) {
    System.out.println(DateUtils.parse("20250502", YYYYMMDD));
  }

}
