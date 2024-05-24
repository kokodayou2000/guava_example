package org.example._4_eventbus.test;


import org.example._4_eventbus.internal.MySubscriber;

public class MySimpleListener2 {

    @MySubscriber(topic = "Topic-1")
    public void test1(String text){
        System.out.println("Topic-1 String test1 listener2: "+text);
    }

    @MySubscriber
    public void test2(String text){
        System.out.println("default String test2 listener2: "+text);
    }

    @MySubscriber
    public void test3(Integer text){
        System.out.println("default Integer test2 listener2: "+text);
    }
}
