package com.atguigu.springcloud.lib;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {


    private AtomicInteger atomicInteger =new AtomicInteger(0);

    public final  int getAndIncrement(){
        int current;
        int next;

        do{
            current=this.atomicInteger.get();
            next=current>=Integer.MAX_VALUE?0:current+1;

        }while(!atomicInteger.compareAndSet(current,next));
        System.out.println("****当前第几次:"+next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstanceList) {

        if(serviceInstanceList==null || serviceInstanceList.size()==0){
            throw new RuntimeException("服务实例不能为null,或服务数量不能为0!");
        }

       int index=getAndIncrement()%serviceInstanceList.size();

        return serviceInstanceList.get(index);
    }
}
