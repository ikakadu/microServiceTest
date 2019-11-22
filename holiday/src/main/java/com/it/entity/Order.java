package com.it.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private int orderId;
    private String userId;
    private Timestamp createDate;
    private Timestamp updateDate;
    private List<Item> itemList;


}
