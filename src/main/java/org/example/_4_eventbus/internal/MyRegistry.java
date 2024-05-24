package org.example._4_eventbus.internal;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.reflect.Member.PUBLIC;

/*
 *
 * 规则
 * ConcurrentHashMap map<String,Queue<Subscribe>>
 *
 * topic1 -> subscriber - subscribeMethod
 *        -> subscriber - subscribeMethod
 *        -> subscriber - subscribeMethod
 * topic2 -> subscriber - subscribeMethod
 *        -> subscriber - subscribeMethod
 *        -> subscriber - subscribeMethod
 *
 *
 */
class MyRegistry {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<MySubscribe>> subscriberContainers = new ConcurrentHashMap<>();



    public void bind(Object subscriber){
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach(m -> tierSubscriber(subscriber,m));

    }

    public ConcurrentLinkedQueue<MySubscribe> scanSubscribers(final String topic){
        return subscriberContainers.getOrDefault(topic,new ConcurrentLinkedQueue<>());
    }
    // 链接 subscriber
    private void tierSubscriber(Object subscriber,Method method){
        MySubscriber mySubscriber = method.getDeclaredAnnotation(MySubscriber.class);
        String topic = mySubscriber.topic();
        subscriberContainers.computeIfAbsent(topic,key->{
            return new ConcurrentLinkedQueue<>();
        });
        subscriberContainers.get(topic).add(new MySubscribe(subscriber,method));
    }

    /**
     * 获取被注解的方法
     * @param subscriber 被 subscriber 注解的对象
     * @return 方法列表
     */
    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> methods = new ArrayList<>();
        Class<?> subscriberClass = subscriber.getClass();
        // 回去
        while (subscriberClass != null){
            Method[] declaredMethods = subscriberClass.getDeclaredMethods();
            Arrays.stream(declaredMethods)
                    .filter(m->
                        // 被 mySubscriber注解 ，参数=1 ，public
                            {
                                boolean res = m.isAnnotationPresent(MySubscriber.class) && m.getParameterCount() == 1 && m.getModifiers() == 1;
                                return res;
                            }
                    )
                    .forEach(methods::add);
            subscriberClass = subscriberClass.getSuperclass();
        }
        return methods;
    }

    /**
     * 解绑并未对 map本身使用 delete 操作，只是使用标识位来标记
     * 使用标记未的优势还有就是可以使用 stream API
     * @param subscriber 解绑的对象
     */
    public void unbind(Object subscriber){
        subscriberContainers.forEach((key,queue)->{
            queue.forEach((s)->{
                if (s.getSubscriberObject() == subscriber){
                    s.setDisabled(true);
                }
            });
        });
    }

}
