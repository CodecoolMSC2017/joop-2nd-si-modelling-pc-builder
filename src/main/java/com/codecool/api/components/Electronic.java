package com.codecool.api.components;

import com.codecool.api.enums.Tier;

public abstract class Electronic extends PCComponent {

    private int powerConsumption;

    public Electronic(String name, String manufacturer, int value, Tier tier, int powerConsumption) {
        super(name, manufacturer, value, tier);
        this.powerConsumption = powerConsumption;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

}