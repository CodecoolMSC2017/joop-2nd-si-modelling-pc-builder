package com.codecool.api.enums;

public enum Tier {

    HIGH(10),
    MEDIUM(7),
    LOW(3);

    private final int fanciness;

    Tier(int fanciness) {
        this.fanciness = fanciness;
    }

    public int getFanciness() {
        return fanciness;
    }

}