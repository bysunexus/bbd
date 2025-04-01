package in.fireye.bbd;

import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, OpenAiAutoConfiguration.class})
public class BbdApplication {

  public static void main(String[] args) {
    SpringApplication.run(BbdApplication.class, args);
  }

}
