package com.codecool;

public enum Temperature {

    MELT     (300),
    OVERHEAT  (90),
    UNDERLOAD (65),
    IDLE      (35),
    AMBIENT   (25);

    private int temperature;

    private Temperature(int temperature) {
        this.temperature = temperature;
    }

    public int inDigits() {
        return temperature;
    }

}