package org.example._4_eventbus;

import com.google.common.eventbus.EventBus;
import org.example._4_eventbus.events.Apple;
import org.example._4_eventbus.events.Fruit;
import org.example._4_eventbus.listener.DeadEventListener;
import org.example._4_eventbus.listener.FruitEaterListener;

/**
 * 任何未被处理的Event都会被 DeadEvent 处理
 */
public class DeadEventEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus("DeadEvent"){
            @Override
            public String toString(){
                return "Dead Event Bus";
            }
        };
        eventBus.register(new DeadEventListener());

        eventBus.post(111);
        eventBus.post("111");
    }
}
