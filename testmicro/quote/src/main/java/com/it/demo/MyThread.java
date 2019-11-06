package com.it.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread {
    public static int cnt = 0;
    public static final int COUNT = 30;

    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new ReentrantLock();

        Thread threadA = new Thread(new Runnable() {
            public void run() {
                while(true){
                    lock.lock();
                    if(cnt>=COUNT){
                        lock.unlock();
                        return;
                    }
                    if(cnt%3==0){
                        System.out.print("A ");
                        cnt++;
                    }
                    lock.unlock();
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    lock.lock();
                    if(cnt>=COUNT){
                        lock.unlock();
                        return;
                    }
                    if(cnt%3==1){
                        System.out.print("B ");
                        cnt++;
                    }
                    lock.unlock();
                }
            }
        });
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    lock.lock();
                    if(cnt>=COUNT){
                        lock.unlock();
                        return;
                    }
                    if(cnt%3==2){
                        System.out.println("C");
                        cnt++;
                    }
                    lock.unlock();
                }
            }
        });

            threadA.start();
//            threadA.join();

            threadB.start();
//            threadB.join();

            threadC.start();
//            threadC.join();
    }
}

class A implements Runnable{

    @Override
    public void run() {

    }
}
class B implements Runnable{

    @Override
    public void run() {
        System.out.print("B ");
    }
}
class C implements Runnable{

    @Override
    public void run() {
        System.out.print("C ");
    }
}