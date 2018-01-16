package com.codecool;

import java.util.Objects;

public class Memory extends Electronic {

    private int capacity;
    private String type;

    public Memory(int capacity, String type, int powerConsumption, String name, String manufacturer, int value, Tier tier) {
        super(powerConsumption, name, manufacturer, value, tier);
        this.capacity = capacity;
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
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
        Memory mem = (Memory)o;
        return Objects.equals(this.getName(), mem.getName());
    }

}