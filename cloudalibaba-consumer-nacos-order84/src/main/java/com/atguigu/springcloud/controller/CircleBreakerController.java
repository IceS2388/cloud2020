package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entites.CommonResult;
import com.atguigu.springcloud.entites.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    @Value("${service-url.nacos-user-service}")
    public String SERVICE_URL;

    @Resource
    private RestTemplate restTemplate;


    //@SentinelResource("fallback")//什么都没配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback")//只配置fallback
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")
    @SentinelResource(value="fallback",fallback = "handlerFallback",blockHandler = "blockHandler")
    @GetMapping("/consumer/fallback/{id}")
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        log.info(SERVICE_URL);

        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常...");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointException,该ID没有对应记录，空指针异常");
        }

        return result;
    }

    public CommonResult<Payment> handlerFallback(@PathVariable Long id, Throwable exception) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(444, "异常兜底方法,exception:" + exception.getMessage(), payment);
    }

    public CommonResult<Payment> blockHandler(@PathVariable Long id, BlockException exception) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(445, "blockHandler限流兜底方法,exception:" + exception.getMessage(), payment);
    }

}
