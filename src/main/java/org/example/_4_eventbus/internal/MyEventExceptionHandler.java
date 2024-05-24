package org.example._4_eventbus.internal;

public interface MyEventExceptionHandler {
    void handle(Throwable cause,MyEventContext context);
}
