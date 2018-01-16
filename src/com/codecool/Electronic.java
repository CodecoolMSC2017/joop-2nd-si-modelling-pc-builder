package com.codecool;

import java.util.Objects;

public class Electronic extends PCComponent {

    private int powerConsumption;

    public Electronic(int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(name, manufacturer, value, tier);
        this.powerConsumption = powerConsumption;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Electronic item = (Electronic)o;
        return Objects.equals(this.getName(), item.getName());
    }

}