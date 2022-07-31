package com.example.demo.user.service.impl;

import com.example.demo.user.entity.Message;
import com.example.demo.user.mapper.MessageMapper;
import com.example.demo.user.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fairy.vip
 * @since 2022-08-01
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
