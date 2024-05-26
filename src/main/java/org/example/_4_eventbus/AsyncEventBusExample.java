package org.example._4_eventbus;

import com.google.common.eventbus.AsyncEventBus;
import org.example._4_eventbus.listener.SimpleListener;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * guava 自带的 异步 eventBus
 */
public class AsyncEventBusExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        AsyncEventBus asyncEventBus = new AsyncEventBus(executorService);
        asyncEventBus.register(new SimpleListener());
        asyncEventBus.post("good");
        executorService.shutdown();
    }
}
