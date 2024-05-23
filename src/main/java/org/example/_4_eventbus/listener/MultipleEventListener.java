package org.example._4_eventbus.listener;

import com.google.common.eventbus.Subscribe;

public class MultipleEventListener {

    @Subscribe
    public void strTask1(String event){
        System.out.println("the task1 event: " + event);
    }

    @Subscribe
    public void strTask2(String event){
        System.out.println("the task2 event: " + event);
    }

    /**
     * 必须是Integer才行
     * @param event
     */
    @Subscribe
    public void intTask3(Integer event){
        System.out.println("the task2 event: " + event);
    }

}
