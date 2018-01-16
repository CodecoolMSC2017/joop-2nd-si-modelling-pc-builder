package com.codecool;

public class ProcessingUnit extends Electronic {

    private int temperature;

    public ProcessingUnit(int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.temperature = 25;
    }

    public int getTemperature() {
        return temperature;
    }

}