package com.example.Swipe.Admin.enums;

public enum HeatingType {
    GAS("Газ"), BOILER("Бойлер");
    private String value;

    HeatingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
