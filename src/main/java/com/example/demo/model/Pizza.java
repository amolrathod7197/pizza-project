package com.example.demo.model;

public class Pizza {

    private int id;
    private String type;
    private String name;
    private String description;
    private float price_regular;
    private float price_medium;
    private float price_large;

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

    public float getPrice_regular() {
        return price_regular;
    }

    public void setPrice_regular(float price_regular) {
        this.price_regular = price_regular;
    }

    public float getPrice_medium() {
        return price_medium;
    }

    public void setPrice_medium(float price_medium) {
        this.price_medium = price_medium;
    }

    public float getPrice_large() {
        return price_large;
    }

    public void setPrice_large(float price_large) {
        this.price_large = price_large;
    }


}
