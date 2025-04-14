package in.fireye.bbd.controller;

import in.fireye.bbd.service.DemoService;
import in.fireye.bbd.vo.MessageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
@Slf4j
public class DemoController {


  private final DemoService demoService;

  // 如使用Server-Sent Events 需要将 produces = MediaType.TEXT_EVENT_STREAM_VALUE，此处仅以流方式返回
  @PostMapping(value = "/chat")
  public Flux<String> time(@RequestBody MessageVO message) {

    return demoService.chat(message.getMessage());
  }
}
