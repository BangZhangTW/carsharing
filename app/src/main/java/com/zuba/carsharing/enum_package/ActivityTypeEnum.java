package com.zuba.carsharing.enum_package;

public enum ActivityTypeEnum {
    MAIN_LOGIN(1),
    LOGIN(2),
    REGISTER(3),
    FORGET_PASSWORD(4),
    HOME(5);;

    private int value;

    ActivityTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}

