package com.example.Swipe.Admin.enums;

public enum State {
    REPAIR("Требуется ремонт"),IDEAL("Идеальное состояние"),SECONDARY("Закаленная в боях");

    private String value;

    State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
