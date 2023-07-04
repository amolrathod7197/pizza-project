package com.example.demo.response;

import com.example.demo.model.Order;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OrderResponse {
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Message")
    private String message;

    @JsonProperty("OrderDetails")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Order order;

    @JsonProperty("Orders")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Order> orderList;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "PizzaResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
