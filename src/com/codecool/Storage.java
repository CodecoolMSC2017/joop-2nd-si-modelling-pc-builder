package com.codecool;

import java.util.Objects;

public class Storage extends Electronic {

    private int capacity;
    private int transferSpeed;

    public Storage(String name, String manufacturer, int value, Tier tier,
    int powerConsumption, int capacity, int transferSpeed) {
        super(name, manufacturer, value, tier, powerConsumption);
        this.capacity = capacity;
        this.transferSpeed = transferSpeed;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTransferSpeed() {
        return transferSpeed;
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
        Storage storage = (Storage)o;
        return Objects.equals(this.getName(), storage.getName());
    }

}