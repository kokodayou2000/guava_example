package org.example._4_eventbus.listener;

import com.google.common.eventbus.Subscribe;

public abstract class BaseListener extends AbstractListener{

    @Subscribe
    public void baseTask(String event){
        System.out.println("The baseTask \t"+ this.getClass().getSimpleName()+ " "+event);
    }
}
