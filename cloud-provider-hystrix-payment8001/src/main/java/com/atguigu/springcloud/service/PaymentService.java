package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK,id:" + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber = 2;
        //final int i = 1 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_TimeOut,id:" + id + "\t 耗时" + timeNumber + "秒";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "/(ToT)/调用8001支付接口超时异常：\t" + "\t当前线程名字:" + Thread.currentThread().getName();
    }
}
