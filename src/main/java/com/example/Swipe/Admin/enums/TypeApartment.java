package com.example.Swipe.Admin.enums;

public enum TypeApartment {
    APARTMENT("Апартаменты"),COMMUNAL("Коммуналка");
    private String value;

    TypeApartment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
