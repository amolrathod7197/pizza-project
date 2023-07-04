package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Customer {

    @JsonProperty("CustomerId")
    private int customerId;
    @JsonProperty("CustomerName")
    @NotBlank(message = "CustomerName is mandatory field")
    private String customerName;
    @NotBlank(message = "CustomerAddress is mandatory field")
    @JsonProperty("CustomerAddress")
    private String address;
    @NotNull(message = "PhoneNumber is mandatory field")
    @JsonProperty("PhoneNumber")
    private Long phoneNumber;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
