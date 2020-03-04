package com.example.testapplication.customView;

public enum TextType {
    NAME(1),
    PHONE_NUMBER(2),
    EMAIL(3);

    int mValue;
    TextType(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return mValue;
    }
}
