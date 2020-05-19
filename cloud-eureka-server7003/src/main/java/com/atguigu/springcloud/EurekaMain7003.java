package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7003 {

    /**
     * 重点是在Idea中,复制文件夹或者代码文件时，
     * 务必进入windows自带的文件浏览中，否则会在复制过程中添加大量相关应用，导致项目报错！
     * */
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7003.class,args);
    }
}
