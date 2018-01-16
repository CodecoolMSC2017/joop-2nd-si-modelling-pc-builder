package com.codecool;

import java.util.Objects;

public class HardDiskDrive extends Storage {

    private int rpm;

    public HardDiskDrive(int rpm, int capacity, int transferSpeed, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(capacity, transferSpeed, powerConsumption, name, manufacturer, value, tier);
        this.rpm = rpm;
    }

    public int getRpm() {
        return rpm;
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
        HardDiskDrive disk = (HardDiskDrive)o;
        return Objects.equals(this.getName(), disk.getName());
    }

}