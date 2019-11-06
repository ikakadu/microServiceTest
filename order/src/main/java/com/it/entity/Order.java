package com.it.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private String orderId;
    private Long userId;
    private Date createDate;
    private Date updateDate;
    private List<OrderDetail> orderDetails;


}
