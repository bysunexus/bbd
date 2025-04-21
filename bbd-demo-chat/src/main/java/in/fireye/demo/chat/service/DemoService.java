package in.fireye.demo.chat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class DemoService {
  private final ChatClient chatClient;

  public Flux<String> chat(String msg) {
    // ollama 在使用tool calling的时候不支持stream
    return chatClient.prompt(msg).stream().content();
  }
}
