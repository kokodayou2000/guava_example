package org.example._4_eventbus;

import com.google.common.eventbus.EventBus;
import org.example._4_eventbus.listener.ConcreteListener;

/**
 * 继承的时候，会触发父类的 subscribe
 */
public class InheritListenerEventBusExample {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new ConcreteListener());
        eventBus.post("event");
    }
}
