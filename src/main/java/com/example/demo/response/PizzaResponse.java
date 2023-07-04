package com.example.demo.response;

import com.example.demo.model.Pizza;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PizzaResponse {
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Pizza")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pizza pizza;

    @JsonProperty("Pizzas")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Pizza> pizzaList;


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

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    @Override
    public String toString() {
        return "PizzaResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + pizza +
                '}';
    }
}
