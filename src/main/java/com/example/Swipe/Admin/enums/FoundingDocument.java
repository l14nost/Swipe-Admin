package com.example.Swipe.Admin.enums;

public enum FoundingDocument {
    OWN("Собственость"),TREATY("Договор");
    private String value;

    FoundingDocument(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
