package com.codecool;

import java.util.Objects;

public class PowerSupply extends Electronic {

    private int performance;

    public PowerSupply(String name, String manufacturer, int value, Tier tier, int powerConsumption, int performance) {
        super(name, manufacturer, value, tier, powerConsumption);
        this.performance = performance;
    }

    public int getPerformance() {
        return performance;
    }

    public String details() {
        return "\n\033[1m   Name: " + this.getName() + "\n" +
               "   Manufacturer: " + this.getManufacturer() + "\n" +
               "   Value: " + this.getValue() + "\n" +
               "   Tier: " + this.getTier() + "\n" +
               "   Power consumption: " + this.getPowerConsumption() + "W\n" +
               "   Performance: " + this.getPerformance() + "W\033[0m";
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
        PowerSupply psu = (PowerSupply)o;
        return Objects.equals(this.getName(), psu.getName());
    }

}