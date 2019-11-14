package com.it.jdbc;

import com.it.entity.Holiday;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestJdbc {

    public static void main(String[] args) {
        System.out.println("asdfsfasf");
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
}
