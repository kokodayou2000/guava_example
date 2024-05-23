package org.example._4_eventbus;


import com.google.common.eventbus.EventBus;
import org.example._4_eventbus.service.QueryService;
import org.example._4_eventbus.service.RequestQueryHandler;

public class ComEachOtherEventBusExample {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        // 使用同一个 eventBus
        RequestQueryHandler requestQueryHandler = new RequestQueryHandler(eventBus);
        QueryService queryService = new QueryService(eventBus);

        // 注册处理器
        eventBus.register(requestQueryHandler);

        queryService.query("orderNo");
    }
}
