package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entites.CommonResult;
import com.atguigu.springcloud.entites.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /*通过‘服务发现’来获取服务的基本信息*/
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(value="/payment/discovery")
    public Object discovery(){

        List<String> services = discoveryClient.getServices();//Spring自带的discoveryClient
        for (String service:services
        ) {
            log.info(service);
        }


        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for( ServiceInstance info:instances){
            log.info(info.getHost()+"\t"+info.getPort()+"\t"+info.getInstanceId()+"\t"+info.getUri());
        }
        return this.discoveryClient;
    }

    @PostMapping(value = "/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        log.info("*****插入结果："+result+"666");

        if(result > 0)
        {
            return new CommonResult(200,"插入数据库成功,serverPort:"+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null)
        {
            return new CommonResult(200,"查询成功,serverPort"+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: "+id,null);
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/payment/timeout")
    public String getPaymentTimeout(){
        try {
            Thread.sleep(3000);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return serverPort;
    }
}
