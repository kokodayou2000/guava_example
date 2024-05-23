package org.example._4_eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.example._4_eventbus.events.Apple;
import org.example._4_eventbus.events.Fruit;

public class FruitEaterListener {

    @Subscribe
    public void eat(Fruit fruit){
        System.out.println("eat Fruit ->  " + fruit.getName());
    }

    @Subscribe
    public void eat(Apple apple){
        System.out.println("eat Apple -> " + apple.getName());
    }

}
