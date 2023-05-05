package com.example.Swipe.Admin.enums;

public enum Calculation {
    RECEIPT("Под расписку"),CAPITAL("Мат. Капитал"),NOTARY("Через нотариус");
    private String value;

    Calculation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
