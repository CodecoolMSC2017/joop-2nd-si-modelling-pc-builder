package com.codecool;

import java.util.Objects;

public class Storage extends Electronic {

    private int capacity;
    private int transferSpeed;

    public Storage(int capacity, int transferSpeed, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.capacity = capacity;
        this.transferSpeed = transferSpeed;
    }

    public int getCapacity() {
        return capacity;
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
        Storage storage = (Storage)o;
        return Objects.equals(this.getName(), storage.getName());
    }

}