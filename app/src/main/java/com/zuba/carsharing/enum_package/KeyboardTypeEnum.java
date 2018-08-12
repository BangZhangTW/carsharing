package com.zuba.carsharing.enum_package;

public enum KeyboardTypeEnum {
    //Search
    SEARCH(1);

    private int value;

    KeyboardTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
