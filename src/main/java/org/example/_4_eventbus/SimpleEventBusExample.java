package org.example._4_eventbus;

import com.google.common.eventbus.EventBus;
import org.example._4_eventbus.listener.SimpleListener;

public class SimpleEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();

        eventBus.register(new SimpleListener());
        eventBus.post("simple event");

    }
}
