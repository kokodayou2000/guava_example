package org.example._4_eventbus.test;

import org.example._4_eventbus.internal.MyAsyncEventBus;
import org.example._4_eventbus.internal.MyEventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 测试异步事件总线
 */
public class MyAsyncEventBusExample {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        MyAsyncEventBus asyncEventBus = new MyAsyncEventBus(poolExecutor);
        asyncEventBus.register(new MySimpleListener3Later());
        asyncEventBus.post("test");

        poolExecutor.shutdown();
    }
}
