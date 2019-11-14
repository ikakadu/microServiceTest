package com.it.jdbc;

//import org.junit.Test;
import com.it.entity.Holiday;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTemplate {
    /*@Test
    public void demo(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/practise?characterEncoding=utf8&serverTimezone=UTC&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);

    }*/

    private JDBCTemplate(){}

    static DriverManagerDataSource dataSource=new DriverManagerDataSource();

    static {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/practise?characterEncoding=utf8&serverTimezone=UTC&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
    }

    private static class JDBCTemplateInstance{
        private static final JdbcTemplate INSTANCE = new JdbcTemplate(dataSource);
    }

    public static JdbcTemplate getInstance(){
        return JDBCTemplateInstance.INSTANCE;
    }

}
