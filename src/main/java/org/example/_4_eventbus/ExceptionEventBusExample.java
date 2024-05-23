package org.example._4_eventbus;

import com.google.common.eventbus.EventBus;
import com.sun.jdi.event.ExceptionEvent;
import org.example._4_eventbus.listener.ExceptionListener;

public class ExceptionEventBusExample {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new ExceptionListener());
        eventBus.post("event bus post");
    }
}
