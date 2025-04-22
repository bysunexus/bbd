package in.fireye.demo.mcp.server;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

  public record Weather(
    String city,
    String weather,
    String temperature,
    String windDirection,
    String windPower
  ) {

  }

  private Weather getWeather(String city) {
    return new Weather(city,"晴天", "25", "北风", "3级");
  }

  @Tool(name="获取城市天气预报",description = "获取指定城市的天气预报")
  public String getWeatherByCity(String city) {
    Weather weather = getWeather(city);
    return String.format(
      """
        %s 今天是 %s
        温度：%s
        风： %s %s
        """,
      weather.city,
      weather.weather,
      weather.temperature,
      weather.windDirection,
      weather.windPower
    );
  }
}
