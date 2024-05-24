package org.example._4_eventbus.internal;

import java.lang.reflect.Method;

public class MySubscribe {

    private final Object subscriberObject;

    private final Method subscriberMethod;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    private boolean disabled = false;

    public MySubscribe(Object subscriberObject, Method subscriberMethod) {
        this.subscriberObject = subscriberObject;
        this.subscriberMethod = subscriberMethod;
    }

    public Object getSubscriberObject() {
        return subscriberObject;
    }

    public Method getSubscriberMethod() {
        return subscriberMethod;
    }
}
