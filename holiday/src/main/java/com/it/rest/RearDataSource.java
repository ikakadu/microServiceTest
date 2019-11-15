package com.it.rest;

import com.alibaba.fastjson.JSON;
import com.it.jdbc.ApplicationConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RearDataSource {


    @Autowired
    ApplicationConfig applicationConfig;

    @GetMapping(value = "/datasou")
    public String jdbcTest(){
        System.out.println(applicationConfig);
        return applicationConfig.toString();

        //查看配置数据源信息
//        System.out.println(dataSource);
//        System.out.println(dataSource.getClass().getName());
//        System.out.println(dataSourceProperties);
//
//
////        执行SQL,输出查到的数据
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        List<?> resultList = jdbcTemplate.queryForList("select * from HOLIDAY");
//        System.out.println("===>>>>>>>>>>>"+ JSON.toJSONString(resultList));

    }
}
