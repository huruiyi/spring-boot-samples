package com.example.web;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.utils.PageData;
import com.example.utils.Response;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.matching;

@CrossOrigin
@RestController
@RequestMapping("message")
public class MessageController {

  private final MessageRepository messageRepository;

  public MessageController(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  /**
   * http://localhost:8090/message/list?type=1&currentPage=1
   *
   */
  @GetMapping("list")
  public PageData<Message> list(int type, int currentPage) {

    Sort sort = Sort.by(Sort.Direction.DESC, "createDate");
    sort = sort.and(Sort.by(Sort.Direction.DESC, "subject"));
    PageRequest pageRequest = PageRequest.of(currentPage - 1, 5, sort);

    ExampleMatcher exampleMatcher =
        matching().withMatcher("type", matcher -> matcher.transform(value -> Optional.of(type)));
    Example<Message> example0 = Example.of(new Message(), exampleMatcher);

    Message message = new Message();
    message.setType(type);
    Example<Message> example = Example.of(message);

    Page<Message> page = messageRepository.findAll(example, pageRequest);
    PageData<Message> res = new PageData<>();
    res.setData(page.getContent());
    res.setCurrentPage(currentPage);
    res.setPageSize(5);
    res.setTotalPage(page.getTotalPages());
    return res;
  }

  @PostMapping("add")
  @ResponseBody
  public Response user(Message message) {
    Response res = new Response();
    try {
      message.setCreateDate(LocalDateTime.now());
      messageRepository.save(message);
      res.setFlag(true);
      res.setMessage("留言成功");
      return res;
    } catch (Exception ex) {
      res.setFlag(false);
      res.setMessage("留言失败");
      return res;
    }
  }
}
