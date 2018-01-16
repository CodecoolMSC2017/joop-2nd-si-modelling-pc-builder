package com.codecool;

public class Memory extends Electronic {

    private int capacity;

    public Memory(int capacity, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.capacity = capacity;
    }

}