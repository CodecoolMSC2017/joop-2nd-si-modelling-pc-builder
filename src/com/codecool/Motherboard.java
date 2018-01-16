package com.codecool;

public class Motherboard extends Electronic {

    private Size size;

    public Motherboard(Size size, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.size = size;
    }

}