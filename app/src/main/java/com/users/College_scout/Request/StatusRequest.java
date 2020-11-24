package com.users.College_scout.Request;

public class StatusRequest {
    String orderId;

    public StatusRequest(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
