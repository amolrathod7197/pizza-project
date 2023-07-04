package com.example.demo.enums;

public enum Size {
    REGULAR, MEDIUM, LARGE;

    public String getSize(String value) {

        // this will refer to the object SMALL
        switch (value) {
            case "REGULAR":
                return "price_regular";

            case "MEDIUM":
                return "price_medium";

            case "LARGE":
                return "price_large";

            default:
                return null;
        }
    }
}
