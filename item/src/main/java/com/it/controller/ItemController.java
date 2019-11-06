package com.it.controller;

import com.it.entity.Item;
import com.it.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/item/{id}" , method = RequestMethod.GET)
    public Item queryItemById(@PathVariable(name = "id") Long id){
        return itemService.queryById(id);
    }
}
