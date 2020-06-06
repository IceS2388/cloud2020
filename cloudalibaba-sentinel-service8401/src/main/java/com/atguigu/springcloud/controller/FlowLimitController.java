package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){

        log.info("------testA");
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB(){
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("------testB");
        return "------testB";
    }
}
