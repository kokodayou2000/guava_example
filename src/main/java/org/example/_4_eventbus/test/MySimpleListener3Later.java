package org.example._4_eventbus.test;


import org.example._4_eventbus.internal.MySubscriber;

import java.util.Timer;

public class MySimpleListener3Later {


    @MySubscriber
    public void test1(String text){
        try {
            System.out.println("延迟执行2s : "+ Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println("延迟执行2s 完成: "+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @MySubscriber
    public void test2Later(String text){
        try {
            System.out.println("延迟执行5s : "+ Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("延迟执行5s 完成: "+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @MySubscriber
    public void test3(String text){
        System.out.println("直接执行 : "+ Thread.currentThread().getName());
    }
}
