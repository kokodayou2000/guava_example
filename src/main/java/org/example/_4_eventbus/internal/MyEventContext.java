package org.example._4_eventbus.internal;

import java.lang.reflect.Method;

public interface MyEventContext {
    //
    String getSource();
    // 执行对象
    Object getSubscriber();
    // 执行的方法
    Method getSubscribeMethod();
    // 事件
    Object getEvent();
}
