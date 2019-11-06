package com.it.entity;

import lombok.Data;

@Data
public class OrderDetail {
    private String orderId;
    private Item item;

    public OrderDetail(String orderId, Item item) {
        this.orderId = orderId;
        this.item = item;
    }
}
