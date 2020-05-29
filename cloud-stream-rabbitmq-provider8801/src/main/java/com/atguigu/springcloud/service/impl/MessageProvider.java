package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;


//@Service 不是与Controller打交道
@EnableBinding(Source.class)//定义消息的推送管道
@Slf4j
public class MessageProvider implements IMessageProvider {

    @Autowired
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {

        String serial= UUID.randomUUID().toString();
        //注意MessageBuilder所属的包
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("****serial:"+serial);
        return serial;
    }
}
