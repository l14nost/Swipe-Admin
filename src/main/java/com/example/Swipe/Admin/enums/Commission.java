package com.example.Swipe.Admin.enums;

public enum Commission {
    K10(10000),K20(20000),K30(30000),K40(40000);
    private int value;

    Commission(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
