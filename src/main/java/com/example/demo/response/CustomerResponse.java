package com.example.demo.response;

import com.example.demo.model.Customer;
import com.example.demo.model.Pizza;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CustomerResponse {
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("Customer")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Customer customer;

    @JsonProperty("Customers")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Customer> customerList;


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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return "PizzaResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
