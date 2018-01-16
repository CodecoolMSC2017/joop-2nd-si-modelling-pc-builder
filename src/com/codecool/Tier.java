package com.codecool;

public enum Tier {

    HIGH  (10),
    MEDIUM (7),
    LOW    (3);

    private final int fanciness;

    private Tier(int fanciness) {
        this.fanciness = fanciness;
    }

    public int getFanciness() {
        return fanciness;
    }

}