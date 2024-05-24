package org.example._4_eventbus.internal;

import java.util.concurrent.Executor;

/**
 * 自定义 EventBus
 */
public class MyEventBus implements Bus {

    private final MyRegistry registry = new MyRegistry();

    private String busName;

    private final static String DEFAULT_BUS_NAME = "default";

    private final MyDispatcher dispatcher;

    public MyEventBus(){
        this(DEFAULT_BUS_NAME,null,MyDispatcher.SEQ_Executor_SERVICE);
    }

    public MyEventBus(String busName){
        this(busName,null,MyDispatcher.SEQ_Executor_SERVICE);
    }

    public MyEventBus(MyEventExceptionHandler exceptionHandler){
        this(DEFAULT_BUS_NAME,exceptionHandler,MyDispatcher.SEQ_Executor_SERVICE);
    }


    public MyEventBus(String busName, MyEventExceptionHandler exceptionHandler, Executor executor){
        this.busName = busName;
        this.dispatcher = MyDispatcher.newDispatcher(exceptionHandler,executor);

    }

    @Override
    public void register(Object subscriber) {
        this.registry.bind(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        this.registry.unbind(subscriber);
    }

    @Override
    public void post(Object event) {
        this.post(event,DEFAULT_BUS_NAME);
    }

    @Override
    public void post(Object event, String topic) {
        // 事件分发
        dispatcher.dispatch(this,registry,event,topic);
    }

    @Override
    public void close() {
        this.dispatcher.close();
    }

    @Override
    public String getBusName() {
        return busName;
    }
}
