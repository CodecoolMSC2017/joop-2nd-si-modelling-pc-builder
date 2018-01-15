package com.codecool;

public class Electronic {

    private String name;
    private String manufacturer;
    private int powerConsumption;
    private int value;

    public Electronic(String name, String manufacturer, int powerConsumption, int value) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.powerConsumption = powerConsumption;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public int getValue() {
        return value;
    }
    
}