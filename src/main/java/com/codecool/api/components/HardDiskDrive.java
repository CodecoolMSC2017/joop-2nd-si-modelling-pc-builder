package com.codecool.api.components;

import com.codecool.api.enums.Tier;

import java.util.Objects;

public class HardDiskDrive extends Storage {

    private int rpm;

    public HardDiskDrive(String name,
                         String manufacturer,
                         int value,
                         Tier tier,
                         int powerConsumption,
                         int capacity,
                         int transferSpeed,
                         int rpm) {
        super(name, manufacturer, value, tier, powerConsumption, capacity, transferSpeed);
        this.rpm = rpm;
    }

    public int getRpm() {
        return rpm;
    }

    public String details() {
        return "Name: " + this.getName() + "\n" +
                "Manufacturer: " + this.getManufacturer() + "\n" +
                "Value: $" + this.getValue() + "\n" +
                "Tier: " + this.getTier() + "\n" +
                "Power consumption: " + this.getPowerConsumption() + "W\n" +
                "Capacity: " + this.getCapacity() + "GB\n" +
                "Transfer speed: " + this.getTransferSpeed() + "Mb/sec\n" +
                "Rotation speed: " + this.getRpm() + " Rpm";
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
        HardDiskDrive disk = (HardDiskDrive) o;
        return Objects.equals(this.getName(), disk.getName());
    }

}