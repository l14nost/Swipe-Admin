package com.example.Swipe.Admin.enums;

public enum TechnologyType {
    MONOLITH("Монолит"), PANEL("Панельное");
    private String value;

    TechnologyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
