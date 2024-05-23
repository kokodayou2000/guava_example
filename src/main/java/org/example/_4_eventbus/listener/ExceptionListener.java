package org.example._4_eventbus.listener;

import com.google.common.eventbus.Subscribe;


public class ExceptionListener {

    @Subscribe
    private void msg1(String event){
        System.out.println("1 -----------");
    }

    @Subscribe
    private void msg2(String event){
        System.out.println("2 -----------");
    }

    @Subscribe
    private void msg3(String event){
        System.out.println("3 -----------");
        throw new RuntimeException("exception");
    }
}
