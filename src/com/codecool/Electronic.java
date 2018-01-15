package com.codecool;

public class Electronic extends PCComponent {

    private Tier tier;

    public Electronic(Tier tier, String name, String manufacturer, int powerConsumption, int value) {
        super(name, manufacturer, powerConsumption, value);
        this.tier = tier;
    }

    public Tier getTier() {
        return tier;
    }

}