package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entites.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * Author:IceS
 * Date:2020-05-14 21:47:27
 * Description:
 * NONE
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
