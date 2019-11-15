package com.it.demo;

public class Test {
    public static void main(String[] args) {
//        int[] res = plusOne(new int[]{1, 2, 3});
        int[] res = uu(new int[]{1, 9, 3});
        System.out.println("qqq: "+ 10%10);
        for(int i:res){
            System.out.println(i);
        }
    }
    public static int[] plusOne(int[] digits) {
        int jinwei = 1;
        for(int i =digits.length-1;i>=0;i--){
            int temp = digits[i] + jinwei;
            digits[i] = (temp)%10;
            jinwei = (temp)/10;
            if(i==0&&jinwei==1){
                int[] res =  new int[digits.length+1];
                for(int k=0;k<digits.length;k++){
                    res[k]=digits[k];
                }
                res[0] =1;
                return res;
            }
        }
        return digits;
    }

    public static int[]  uu(int[] digits){//推荐写法
        for(int i = digits.length - 1; i >= 0; i --) {
            if(digits[i] != 9) {
                digits[i] ++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        return newDigits;
    }
}
