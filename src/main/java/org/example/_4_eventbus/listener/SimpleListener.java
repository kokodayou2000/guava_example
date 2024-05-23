package org.example._4_eventbus.listener;

import com.google.common.eventbus.Subscribe;

public class SimpleListener {

    /**
     * 使用 subscribe 注解来让 post 触发
     * @param event 事件
     */
    @Subscribe
    public void doAction(final String event){
        System.out.println("doAction "+ event );
    }

}
