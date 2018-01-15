package com.codecool;

public class PCComponent extends Electronic {

    private Tier tier;

    public PCComponent(Tier tier, String name, String manufacturer, int powerConsumption, int value) {
        super(name, manufacturer, powerConsumption, value);
        this.tier = tier;
    }

    public Tier getTier() {
        return tier;
    }

}