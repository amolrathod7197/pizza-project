package com.example.demo.response;


import com.fasterxml.jackson.annotation.JsonProperty;


public class OrderDetail {

    @JsonProperty("PizzaId")
    private int pizzaId;
    @JsonProperty("Size")
    private String size;
    @JsonProperty("Quantity")
    private int quantity;
    @JsonProperty("Amount")
    private int amount;

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderDetail{");
        sb.append("pizzaId=").append(pizzaId);
        sb.append(", size='").append(size).append('\'');
        sb.append(", quantity='").append(quantity).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
