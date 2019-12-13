package com.it.jdbc;

import com.alibaba.fastjson.JSON;
import com.it.entity.Holiday;
//import org.junit.jupiter.api.Test;
import com.it.entity.Item;
import com.it.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestJdbc {
//    @Autowired
//    DataSourceProperties dataSourceProperties;
//
//    @Autowired
//    ApplicationContext applicationContext;

    @Autowired
    ApplicationConfig applicationConfig;

    @Test
    public void jdbcTest(){
        System.out.println(applicationConfig);

        //获取配置的数据源
//        DataSource dataSource = applicationContext.getBean(DataSource.class);
        //查看配置数据源信息
//        System.out.println(dataSource);
//        System.out.println(dataSource.getClass().getName());
//        System.out.println(dataSourceProperties);


        //执行SQL,输出查到的数据
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        List<?> resultList = jdbcTemplate.queryForList("select * from HOLIDAY");
//        System.out.println("===>>>>>>>>>>>"+ JSON.toJSONString(resultList));

    }


    @Test
    public void holidayTest(){
        List<Object> args = new ArrayList<Object>();
        List<Holiday> list = new ArrayList<Holiday>();
        String sql = "SELECT *  FROM HOLIDAY ";
        try {
//            JdbcTemplate jdbcTemplate = JDBCTemplate.getInstance();
            list = JDBCTemplate.getInstance().query(sql, new RowMapper<Holiday>() {
                @Override
                public Holiday mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Holiday holiday = new Holiday();
                    holiday.setId(rs.getInt("id"));
                    holiday.setRegion(rs.getString("region"));
                    holiday.setLocalDate(rs.getDate("local_date"));
                    holiday.setState(rs.getString("state"));
                    return holiday;
                }
            }, args.toArray());
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(list);
    }

    @Test
    public void orderTest(){
        List<Object> args = new ArrayList<Object>();
        List<Order> list = new ArrayList<Order>();
        String sql = "SELECT ordr.order_id o_order_id,ordr.user_id,ordr.create_time ,item.item_id,item.title,item.price   from  my_order  ordr left JOIN item on ordr.order_id = item.order_id; ";
        list = JDBCTemplate.getInstance().query(sql, new RowMapper<Order>() {
                List<Item> itemList = new ArrayList<Item>();
            @Override
            public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                Order order = new Order();

                order.setOrderId(rs.getInt("o_order_id"));
                order.setUserId(rs.getString("user_id"));
                order.setCreateDate(rs.getTimestamp("create_time"));

                Item item = new Item();
                item.setId(rs.getInt("item_id"));
                item.setTitle(rs.getString("title"));
                item.setPrice(rs.getBigDecimal("price").toPlainString());
                itemList.add(item);
                order.setItemList(itemList);
                return order;
            }
        }, args.toArray());

        System.out.println(list);
    }
}
