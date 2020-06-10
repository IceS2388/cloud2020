package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    @Override
    public void crate(Order order) {
        log.info("---->开始新建订单:");
        orderDao.crate(order);

        log.info("---->订单微服务开始调用库存，做扣减");
        storageService.decrease(order.getProductId(),order.getCount());


        log.info("---->账号余额做扣减");
        accountService.decrease(order.getUserId(),order.getMoney());

        log.info("---->修改订单的状态");
        orderDao.update(order.getUserId(),0);

        log.info("--->下订单结束了");
    }
}
