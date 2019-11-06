package com.it.service;

import com.it.entity.Item;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class ItemService {
    private static final Map<Long, Item> MAP = new HashMap<Long,Item>();

    static{
        for (int i =0;i<10;i++) {
            MAP.put(new Long(i),new Item(new Long(i),"商品标题"+i,"http://图片"+i,"商品描述"+i,new Long(i*10)));
        }
    }

    public Item queryById(Long id){
        return MAP.get(id);
    }
}
