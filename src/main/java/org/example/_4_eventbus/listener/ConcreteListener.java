package org.example._4_eventbus.listener;

import com.google.common.eventbus.Subscribe;

public class ConcreteListener extends BaseListener{

    @Subscribe
    public void concreteTask(String event){
        System.out.println("The concreteTask \t"+ this.getClass().getSimpleName()+"  event "+event);
    }

}
