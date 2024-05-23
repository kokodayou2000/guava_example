package org.example._4_eventbus.events;

import lombok.Data;

@Data
public class Request {

    private final String orderNo;
    public Request(String orderNo) {
        this.orderNo = orderNo;
    }
}
