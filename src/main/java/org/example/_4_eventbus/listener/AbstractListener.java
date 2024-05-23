package org.example._4_eventbus.listener;


import com.google.common.eventbus.Subscribe;

public class AbstractListener {

    @Subscribe
    public void commonTask(String event){
        System.out.println("The commonTask \t"+ this.getClass().getSimpleName()+"  event "+event);
    }
}
