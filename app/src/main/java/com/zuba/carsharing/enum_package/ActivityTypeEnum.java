package com.zuba.carsharing.enum_package;

public enum ActivityTypeEnum {
    MAIN_LOGIN(1),
    LOGIN(2);

    private int value;

    ActivityTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

