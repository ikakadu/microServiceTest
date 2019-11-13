package com.it.demo;

import java.util.Calendar;

public class TimeTest {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(1900, 0, 1);


        System.out.println(Calendar.DATE);
    }
}
