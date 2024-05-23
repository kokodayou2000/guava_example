package org.example._4_eventbus;

import com.google.common.eventbus.EventBus;
import org.example._4_eventbus.listener.MultipleEventListener;
import org.example._4_eventbus.listener.SimpleListener;

public class MultipleEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();

        eventBus.register(new MultipleEventListener());

        eventBus.post("simple event");
        eventBus.post(100);


    }
}
