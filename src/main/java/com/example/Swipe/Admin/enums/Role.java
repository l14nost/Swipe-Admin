package com.example.Swipe.Admin.enums;

public enum Role {
    ADMIN("ADMIN");
    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
