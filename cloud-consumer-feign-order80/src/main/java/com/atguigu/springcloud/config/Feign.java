package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Feign {
    @Bean
    Logger.Level feignLoggerLevel(){//设置Feign的日志级别
        return Logger.Level.FULL;
    }
}
