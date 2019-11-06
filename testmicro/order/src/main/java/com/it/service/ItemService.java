package com.it.service;

import com.it.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class ItemService {

    @Autowired
    RestTemplate restTemplate;

    public Item queryItemById(Long id){
        Item item = restTemplate.getForObject("http://localhost:8080/item/" + id, Item.class);
        return item;
    }
}
