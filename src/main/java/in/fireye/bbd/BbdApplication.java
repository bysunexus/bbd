package in.fireye.bbd;

import in.fireye.bbd.service.DemoService;
import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, OpenAiAutoConfiguration.class})
public class BbdApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(BbdApplication.class, args);

    DemoService service = ctx.getBean(DemoService.class);
    System.out.println("请输入问题：");
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String next = scanner.next();
      if ("exit".equals(next) || "quit".equals(next)) {
        System.exit(0);
      }
      System.out.println("===================================");
      service.chat(next).subscribe(System.out::println);
      System.out.println("===================================");
    }
  }

}
