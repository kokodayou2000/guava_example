package org.example._4_eventbus.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 *
 */
public class MyDispatcher {

    private final Executor executorService;

    private final MyEventExceptionHandler exceptionHandler;


    public static final Executor SEQ_Executor_SERVICE = SeqExecutorService.INSTANCE;

    public static final Executor PRE_Executor_SERVICE = PreThreadExecutorService.INSTANCE;

    public MyDispatcher(MyEventExceptionHandler exceptionHandler, Executor executorService) {
        this.executorService = executorService;
        this.exceptionHandler = exceptionHandler;
    }

    public void dispatch(Bus bus, MyRegistry registry, Object event, String topic) {
        ConcurrentLinkedQueue<MySubscribe> subscribes = registry.scanSubscribers(topic);
        if (subscribes.isEmpty()) {
            if (exceptionHandler != null) {
                // create Context
                BaseMyEventContext context = new BaseMyEventContext(bus.getBusName(), null, event);
                exceptionHandler.handle(new IllegalArgumentException("The topic: " + topic + " not bind yet"), context);
            }
            return;
        }
        // 非禁用状态
        subscribes.stream()
                .filter((
                        subscriber ->
                                !subscriber.isDisabled()
                )).filter(
                        s -> {
                            Method subscriberMethod = s.getSubscriberMethod();
                            // 限定了只能有一个参数
                            Class<?> aClass = subscriberMethod.getParameterTypes()[0];
                            // 确定类是一致的 比如 post("str") 对应 doAction(String str)
                            return (aClass.isAssignableFrom(event.getClass()));
                        }
                ).forEach(o -> {
                    realInvokeSubscriber(o, event, bus);
                });

    }

    private void realInvokeSubscriber(MySubscribe subscribe, Object event, Bus bus) {
        Object subscriberObject = subscribe.getSubscriberObject();
        Method subscriberMethod = subscribe.getSubscriberMethod();
        executorService.execute(() -> {
            try {
                subscriberMethod.invoke(subscriberObject, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                if (exceptionHandler != null) {
                    System.out.println("执行方法的时候捕获到了异常");
                    exceptionHandler.handle(e, new BaseMyEventContext(bus.getBusName(), subscribe, event));
                }
            }
        });
    }

    public void close() {
        if (executorService instanceof ExecutorService) {
            ((ExecutorService) executorService).shutdown();
        }
    }

    static MyDispatcher newDispatcher(MyEventExceptionHandler exceptionHandler, Executor executorService) {
        return new MyDispatcher(exceptionHandler, executorService);
    }

    static MyDispatcher seqDispatcher(MyEventExceptionHandler exception) {
        return new MyDispatcher(exception, SeqExecutorService.INSTANCE);
    }

    static MyDispatcher preThreadDispatcher(MyEventExceptionHandler exception) {
        return new MyDispatcher(exception, PreThreadExecutorService.INSTANCE);
    }

    /**
     * 串行执行
     */
    private static class SeqExecutorService implements Executor {

        private final static SeqExecutorService INSTANCE = new SeqExecutorService();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    private static class PreThreadExecutorService implements Executor {

        private final static PreThreadExecutorService INSTANCE = new PreThreadExecutorService();

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }

    private static class BaseMyEventContext implements MyEventContext {

        private final String eventBusName;

        private final MySubscribe subscribe;

        private final Object event;

        private BaseMyEventContext(String eventBusName, MySubscribe subscribe, Object event) {
            this.eventBusName = eventBusName;
            this.subscribe = subscribe;
            this.event = event;
        }

        @Override
        public String getSource() {
            return this.eventBusName;
        }

        @Override
        public Object getSubscriber() {
            return subscribe != null ? subscribe.getSubscriberObject() : null;
        }

        @Override
        public Method getSubscribeMethod() {
            return subscribe != null ? subscribe.getSubscriberMethod() : null;
        }

        @Override
        public Object getEvent() {
            return this.event;
        }
    }
}
