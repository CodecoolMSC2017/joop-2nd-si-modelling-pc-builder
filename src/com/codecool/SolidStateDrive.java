package com.codecool;

import java.util.Objects;

public class SolidStateDrive extends Storage {

    public SolidStateDrive(String name, String manufacturer, int value, Tier tier,
    int powerConsumption, int capacity, int transferSpeed) {
        super(name, manufacturer, value, tier, powerConsumption, capacity, transferSpeed);
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
        SolidStateDrive disk = (SolidStateDrive)o;
        return Objects.equals(this.getName(), disk.getName());
    }

}