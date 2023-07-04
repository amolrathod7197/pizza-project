package com.example.demo.model;

import com.example.demo.response.OrderDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Order {

    @JsonProperty("OrderId")
    private int orderId;
    @JsonProperty("CustomerId")
    private int customerId;
    @JsonProperty("CustomerAddress")
    private String address;
    @JsonProperty("TotalAmount")
    private int amount;
    @JsonProperty("OrderDateTime")
    private String orderDateTime;
    @JsonProperty("Pizzas")
    private List<OrderDetail> pizzaList;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<OrderDetail> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<OrderDetail> pizzaList) {
        this.pizzaList = pizzaList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", customerId=").append(customerId);
        sb.append(", address='").append(address).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", orderDateTime='").append(orderDateTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
