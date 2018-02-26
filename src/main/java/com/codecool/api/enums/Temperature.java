package com.codecool.api.enums;

public enum Temperature {

    MELT(300),
    OVERHEAT(90),
    UNDERLOAD(65),
    IDLE(35),
    AMBIENT(25);

    private int temperature;

    Temperature(int temperature) {
        this.temperature = temperature;
    }

    public int inDigits() {
        return temperature;
    }

}