package org.example._4_eventbus.test;


import org.example._4_eventbus.internal.MySubscriber;

public class MySimpleListener1 {

    @MySubscriber(topic = "Topic-1")
    public void test1(String text){
        System.out.println("Topic-1 String test1 listener1: "+text);
        throw new RuntimeException("自定义 runtimeException");
    }

    @MySubscriber
    public void test2(String text){
        System.out.println("default String test2 listener1: "+text);
    }

    @MySubscriber
    public void test3(Integer text){
        System.out.println("default Integer test2 listener1: "+text);
    }
}
