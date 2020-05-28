package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "fall back PaymentFallbackService:paymentInfo_OK"+id;
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "fall back PaymentFallbackService:paymentInfo_TimeOut"+id;
    }
}
