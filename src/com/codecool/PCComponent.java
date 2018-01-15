package com.codecool;

public class PCComponent {

    private String name;
    private String manufacturer;
    private int value;
    private Tier tier;

    public PCComponent(String name, String manufacturer, int value, Tier tier) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.value = value;
        this.tier = tier;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getValue() {
        return value;
    }

    public Tier getTier() {
        return tier;
    }

}