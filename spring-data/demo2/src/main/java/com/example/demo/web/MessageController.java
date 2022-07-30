package com.example.demo.web;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.PageData;
import com.example.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
     * @param type
     * @param currentPage
     * @return
     */
    @GetMapping("list")
    public PageData<Message> list(int type, int currentPage) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, 5, Sort.by(Sort.Direction.DESC, "createDate"));
        Example<Message> example = Example.of(new Message(), matching().withMatcher("type", matcher -> matcher.transform(value -> Optional.of(type))));
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
