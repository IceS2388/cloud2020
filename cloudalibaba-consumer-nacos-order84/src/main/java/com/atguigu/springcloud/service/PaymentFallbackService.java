package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entites.CommonResult;
import com.atguigu.springcloud.entites.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements IPaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<Payment>(444,"服务降级返回，---PaymentFallbackService",new Payment(id,"error serials "+id));
    }
}
