package com.codecool;

import java.util.Objects;

public class SolidStateDrive extends Storage {

    public SolidStateDrive(int capacity, int transferSpeed, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(capacity, transferSpeed, powerConsumption, name, manufacturer, value, tier);
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
        SolidStateDrive disk = (SolidStateDrive)o;
        return Objects.equals(this.getName(), disk.getName());
    }

}