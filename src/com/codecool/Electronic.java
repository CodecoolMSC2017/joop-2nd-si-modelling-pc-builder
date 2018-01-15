package com.codecool;

public class Electronic extends PCComponent {

    private int powerConsumption;

    public Electronic(int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(name, manufacturer, value, tier);
        this.powerConsumption = powerConsumption;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

}