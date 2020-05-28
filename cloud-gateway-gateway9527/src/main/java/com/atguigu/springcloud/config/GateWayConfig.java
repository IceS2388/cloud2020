package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder){

        return routeLocatorBuilder.routes()
                .route("path_route_atguigu",r->r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .route("path_route_gj",r->r.path("/guoji").uri("http://news.baidu.com/guoji"))
                .build();

    }

}
