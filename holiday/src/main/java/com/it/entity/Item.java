package com.it.entity;

import lombok.Data;

/**
 * @Description: TODO
 * @Author: wangruitao
 * @DATE: 2019/11/22
 **/
@Data
public class Item {
    private int id;
    private String title;
    private String pic;
    private String desc;
    private String price;
}
