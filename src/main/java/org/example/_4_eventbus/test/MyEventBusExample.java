package org.example._4_eventbus.test;

import org.example._4_eventbus.internal.MyEventBus;

public class MyEventBusExample {
    public static void main(String[] args) {
        MyEventBus eventBus = new MyEventBus((cause, context) -> {
            // 自定义异常
            System.out.println("Received event: " + cause);
            System.out.println("异常发生的 EventBus: " + context.getSource());
            System.out.println("异常发生的 事件: " + context.getEvent());
            System.out.println("异常发生的 类: " + context.getSubscriber());
            System.out.println("异常发生的 方法: " + context.getSubscribeMethod());
            // 打印错误栈
            cause.printStackTrace();

        });

        eventBus.register(new MySimpleListener1());
        eventBus.register(new MySimpleListener2());

        eventBus.post("Hello World");
        eventBus.post(999);

        eventBus.post("Hello World","Topic-1");

    }
}
