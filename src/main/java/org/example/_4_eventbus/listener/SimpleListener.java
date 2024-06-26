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

    /**
     * 使用 subscribe 注解来让 post 触发
     * @param event 事件
     */
    @Subscribe
    public void doAction1(final String event){
        try {
            System.out.println("延迟执行2s : "+ Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println("延迟执行2s 完成: "+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 subscribe 注解来让 post 触发
     * @param event 事件
     */
    @Subscribe
    public void doAction2(final String event){
        try {
            System.out.println("延迟执行5s : "+ Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("延迟执行5s 完成: "+ Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
