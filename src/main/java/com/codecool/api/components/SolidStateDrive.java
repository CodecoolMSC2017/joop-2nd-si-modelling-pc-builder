package com.codecool.api.components;

import com.codecool.api.enums.Tier;

import java.util.Objects;

public class SolidStateDrive extends Storage {

    public SolidStateDrive(String name,
                           String manufacturer,
                           int value,
                           Tier tier,
                           int powerConsumption,
                           int capacity,
                           int transferSpeed) {
        super(name, manufacturer, value, tier, powerConsumption, capacity, transferSpeed);
    }

    public String details() {
        return "Name: " + this.getName() + "\n" +
                "Manufacturer: " + this.getManufacturer() + "\n" +
                "Value: $" + this.getValue() + "\n" +
                "Tier: " + this.getTier() + "\n" +
                "Power consumption: " + this.getPowerConsumption() + "W\n" +
                "Capacity: " + this.getCapacity() + "GB\n" +
                "Transfer speed: " + this.getTransferSpeed() + "Mb/sec";
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
        SolidStateDrive disk = (SolidStateDrive) o;
        return Objects.equals(this.getName(), disk.getName());
    }

}