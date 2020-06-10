package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //1.新建订单
    void crate(Order order);

    //2.修改订单状态0-1
    void update(@Param("userId") Long userId,@Param("status") Integer status);
}
