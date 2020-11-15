package com.users.College_scout;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class OrdersModel{

    @SerializedName("orders")
    private List<Order> orders;

    @SerializedName("message")
    private String message;

    public List<Order> getOrders(){
        return orders;
    }

    public String getMessage(){
        return message;
    }
}

