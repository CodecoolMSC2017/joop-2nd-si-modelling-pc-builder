package com.codecool;

import java.util.Objects;

public class HardDiskDrive extends Storage {

    private int rpm;

    public HardDiskDrive(String name, String manufacturer, int value, Tier tier,
    int powerConsumption, int capacity, int transferSpeed, int rpm) {
        super(name, manufacturer, value, tier, powerConsumption, capacity, transferSpeed);
        this.rpm = rpm;
    }

    public int getRpm() {
        return rpm;
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
        HardDiskDrive disk = (HardDiskDrive)o;
        return Objects.equals(this.getName(), disk.getName());
    }

}