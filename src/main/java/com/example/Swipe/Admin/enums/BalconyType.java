package com.example.Swipe.Admin.enums;

public enum BalconyType {
    YES("Да"), NO("Нет");
    private String value;

    BalconyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
