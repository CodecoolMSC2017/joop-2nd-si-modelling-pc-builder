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