package com.it.service;

import com.it.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import com.netflix.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;


@Service
public class ItemService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

   /* public Item queryItemById(Long id){
        Item item = restTemplate.getForObject("http://localhost:8080/item/" + id, Item.class);
        return item;
    }*/

    public Item queryItemById(Long id){
        String serviceId = "microservice-item";
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        if(instances == null || instances.isEmpty()){
            return null;
        }
        ServiceInstance instance = instances.get(0);
        String url = "http://" + instance.getHost() + ":" + instance.getPort();
        Item item = restTemplate.getForObject(url + "/item/" + id, Item.class);

        return item;
    }
}
