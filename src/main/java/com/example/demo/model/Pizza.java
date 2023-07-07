package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class Pizza {

    @JsonProperty("Id")
    private int id;
    @NotBlank(message = "Type is mandatory field")
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Name")
    @NotBlank(message = "Name is mandatory field")
    private String name;
    @NotBlank(message = "Description is mandatory field")
    @JsonProperty("Description")
    private String description;
    @JsonProperty("PriceRegular")
    @NotNull(message = "PriceRegular is mandatory field")
    private Integer priceRegular;
    @NotNull(message = "PriceMedium is mandatory field")
    @JsonProperty("PriceMedium")
    private Integer priceMedium;
    @JsonProperty("PriceLarge")
    @NotNull(message = "PriceLarge is mandatory field")
    private Integer priceLarge;

    @JsonProperty("AvailableQuantity")
    private Integer availableQuantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriceRegular() {
        return priceRegular;
    }

    public void setPriceRegular(Integer priceRegular) {
        this.priceRegular = priceRegular;
    }

    public Integer getPriceMedium() {
        return priceMedium;
    }

    public void setPriceMedium(Integer priceMedium) {
        this.priceMedium = priceMedium;
    }

    public Integer getPriceLarge() {
        return priceLarge;
    }

    public void setPriceLarge(Integer priceLarge) {
        this.priceLarge = priceLarge;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
