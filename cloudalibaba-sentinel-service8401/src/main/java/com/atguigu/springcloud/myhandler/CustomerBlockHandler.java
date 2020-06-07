package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entites.CommonResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerBlockHandler {

    public static CommonResult handlerException1(BlockException exception) {
        return new CommonResult(444, "按客户自定义的CustomerBlockHandler1！");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "按客户自定义的CustomerBlockHandler2！");
    }
}
