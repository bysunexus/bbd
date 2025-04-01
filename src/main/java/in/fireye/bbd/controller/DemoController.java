package in.fireye.bbd.controller;

import in.fireye.bbd.tools.DateTimeTools;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {


  @Resource
  private OllamaChatModel model;

  @Resource
  private DateTimeTools dateTimeTools;


  @PostMapping("/chat")
  public Flux<String> chat(@RequestParam("message") String message) {
    return model.stream(message);
  }

  @PostMapping("/time")
  public String time(@RequestParam("message") String message) {
    String result = ChatClient.create(model)
      .prompt(message)
      .tools(dateTimeTools)
      .call()
      .content();

    return result;
  }
}
