package org.example._4_eventbus.service;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.example._4_eventbus.events.Request;
import org.example._4_eventbus.events.Response;

/**
 * 查询服务，
 */
public class QueryService {

    private final EventBus eventBus;

    public QueryService(EventBus eventBus) {
        this.eventBus = eventBus;
        this.eventBus.register(this);
    }

    public void query(String orderNo){
        System.out.println("query: "+orderNo);
        System.out.println("发送请求...");
        this.eventBus.post(new Request(orderNo));
    }

    /**
     * 订阅查询的结果
     * @param response 响应数据
     */
    @Subscribe
    public void handlerResponse(Response response){
        String orderNo = response.getOrderNo();
        System.out.println("response:"+orderNo);
    }

}
