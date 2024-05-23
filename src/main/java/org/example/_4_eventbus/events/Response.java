package org.example._4_eventbus.events;

import lombok.Data;

@Data
public class Response {

    private final String orderNo;

    public Response(String orderNo) {
        this.orderNo = orderNo;
    }
}
