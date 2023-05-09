package com.example.Swipe.Admin.enums;

public enum GasType {
    YES("Да"), NO("Нет");
    private String value;

    GasType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
