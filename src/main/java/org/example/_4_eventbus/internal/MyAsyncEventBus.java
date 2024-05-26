package org.example._4_eventbus.internal;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步的事件总线
 */
public class MyAsyncEventBus extends MyEventBus {

    public MyAsyncEventBus(
            String busName,
            MyEventExceptionHandler exceptionHandler,
            ThreadPoolExecutor executor
            ){
        super(busName,exceptionHandler,executor);
    }

    public MyAsyncEventBus(ThreadPoolExecutor executor){
        this("async-default",null, executor);
    }

    public MyAsyncEventBus(String busName,ThreadPoolExecutor executor){
        this(busName,null, executor);
    }

    public MyAsyncEventBus(MyEventExceptionHandler exceptionHandler){
        this("sync-default",exceptionHandler, (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    public MyAsyncEventBus(String busName){
        this(busName,null, (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    public MyAsyncEventBus(){
        this("sync-default",null, (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }
}
