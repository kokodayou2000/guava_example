package org.example._4_eventbus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import org.example._4_eventbus.listener.ExceptionListener;

import java.lang.reflect.Method;

public class ExceptionEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus(new ExceptionHandler());
        eventBus.register(new ExceptionListener());
        eventBus.post("event bus post");
    }
    // 重写 SubscriberExceptionHandler
    static class ExceptionHandler implements SubscriberExceptionHandler{

        @Override
        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            // 事件内容
            Object event = context.getEvent();
            System.out.println(event);
            // 事件总线
            EventBus eventBus = context.getEventBus();
            System.out.println(eventBus);
            // 订阅者
            Object subscriber = context.getSubscriber();
            System.out.println(subscriber);
            // 执行的方法 msg3
            Method subscriberMethod = context.getSubscriberMethod();
            System.out.println(subscriberMethod);
        }
    }
}
