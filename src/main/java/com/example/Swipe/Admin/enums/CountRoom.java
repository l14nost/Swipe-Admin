package com.example.Swipe.Admin.enums;

public enum CountRoom {
    K1(1),K2(2),K3(3),K4(4),K5(5);
    private int value;

    CountRoom(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
