package org.example._4_eventbus.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.example._4_eventbus.events.Request;
import org.example._4_eventbus.events.Response;

/**
 * 请求处理
 */
public class RequestQueryHandler {

    private final EventBus eventBus;

    public RequestQueryHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    /**
     * 当触发的时候，执行查询，然后将相应的结果post到
     * @param req 请求
     */
    @Subscribe
    public void doQuery(Request req){
        System.out.println("查询到了: "+req.getOrderNo());
        Response response = new Response(req.getOrderNo());
        this.eventBus.post(response);
    }

}
