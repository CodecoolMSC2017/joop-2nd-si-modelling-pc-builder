package com.codecool.api.enums;

public enum Size {

    L(3),
    M(2),
    S(1);

    int value;

    Size(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}