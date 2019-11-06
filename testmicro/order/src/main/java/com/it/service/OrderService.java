package com.it.service;

import com.it.entity.Item;
import com.it.entity.Order;
import com.it.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class OrderService {
    private static final Map<String, Order> MAP = new HashMap<String,Order>();

    static{
        Order order = new Order();
        order.setOrderId("123456");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());
        order.setUserId(1L);

        List<OrderDetail> orderDetails = new ArrayList<>();

        Item item = new Item();
        item.setId(1L);
        orderDetails.add(new OrderDetail(order.getOrderId(),item));

        item = new Item();
        item.setId(2L);
        orderDetails.add(new OrderDetail(order.getOrderId(),item));

        order.setOrderDetails(orderDetails);

        MAP.put(order.getOrderId(),order);
    }

    @Autowired
    ItemService itemService;

    public Order queryOrderByid(String orderId){
        Order order = MAP.get(orderId);
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for(OrderDetail OrderDetail:orderDetails){
            Long id = OrderDetail.getItem().getId();
            Item item = itemService.queryItemById(id);
            OrderDetail.setItem(item);
        }

        return order;
    }
}
