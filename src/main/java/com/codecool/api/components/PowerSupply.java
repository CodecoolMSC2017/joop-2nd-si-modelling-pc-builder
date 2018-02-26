package com.codecool.api.components;

import com.codecool.api.enums.Tier;

import java.util.Objects;

public class PowerSupply extends Electronic {

    private int performance;

    public PowerSupply(String name,
                       String manufacturer,
                       int value,
                       Tier tier,
                       int powerConsumption,
                       int performance) {
        super(name, manufacturer, value, tier, powerConsumption);
        this.performance = performance;
    }

    public int getPerformance() {
        return performance;
    }

    public String details() {
        return "Name: " + this.getName() + "\n" +
                "Manufacturer: " + this.getManufacturer() + "\n" +
                "Value: $" + this.getValue() + "\n" +
                "Tier: " + this.getTier() + "\n" +
                "Performance: " + this.getPerformance() + "W";
    }

    @Override
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
        PowerSupply psu = (PowerSupply) o;
        return Objects.equals(this.getName(), psu.getName());
    }

}