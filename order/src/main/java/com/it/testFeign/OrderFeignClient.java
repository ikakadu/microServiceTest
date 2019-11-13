package com.it.testFeign;

import com.it.entity.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient
public class OrderFeignClient {
    @PostMapping("/item/{itemId}")
    Item queryItem(@PathVariable("itemId") String itemId){
        System.out.println("开始查询订单信息");


        return null;
    }
}
