package com.codecool;

public enum Size {

    L (3),
    M (2),
    S (1);

    int value;

    private Size(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}