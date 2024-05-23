package org.example._4_eventbus;

import com.google.common.eventbus.EventBus;
import org.example._4_eventbus.events.Apple;
import org.example._4_eventbus.events.Fruit;
import org.example._4_eventbus.listener.FruitEaterListener;

/**
 * 事件的继承关系
 */
public class InheritEventEventBusExample {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new FruitEaterListener());

        Fruit fruit = new Fruit("fruit");
        Apple apple = new Apple("apple");


        eventBus.post(fruit);
        System.out.println("-------");
        eventBus.post(apple);
    }
}
